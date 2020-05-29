package io.github.portlek.reflection.clazz;

import io.github.portlek.reflection.*;
import io.github.portlek.reflection.constructor.ConstructorOf;
import io.github.portlek.reflection.field.FieldOf;
import io.github.portlek.reflection.method.MethodOf;
import io.github.portlek.reflection.parameterized.ParameterizedOf;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public final class ClassOf<T> implements RefClass<T> {

    @NotNull
    private final Class<T> clazz;

    public ClassOf(@NotNull final Class<T> clazz) {
        this.clazz = clazz;
    }

    public ClassOf(@NotNull final Object object) {
        this((Class<T>) object.getClass());
    }

    public ClassOf(@NotNull final String classname) throws ClassNotFoundException {
        this(Class.forName(classname));
    }

    @Override
    public @NotNull Class<T> getRealClass() {
        return this.clazz;
    }

    @Override
    public boolean isInstance(@NotNull final Object object) {
        return this.clazz.isInstance(object);
    }

    @NotNull
    @Override
    public Optional<RefMethod> getPrimitiveMethod(@NotNull final String name, @NotNull final Object... types) {
        return this.getMethod0(name, true, types);
    }

    @NotNull
    @Override
    public Optional<RefMethod> getMethod(@NotNull final String name, @NotNull final Object... types) {
        return this.getMethod0(name, false, types);
    }

    @Override
    @NotNull
    public Optional<RefMethod> findPrimitiveMethodByParameter(@NotNull final Object... types) {
        return this.findMethod0(true, types);
    }

    @Override
    @NotNull
    public Optional<RefMethod> findMethodByParameter(@NotNull final Object... types) {
        return this.findMethod0(false, types);
    }

    @NotNull
    @Override
    public Optional<RefMethod> findMethodByName(@NotNull final String... names) {
        final Collection<Method> methods = new ArrayList<>(Arrays.asList(this.clazz.getMethods()));
        methods.addAll(Arrays.asList(this.clazz.getDeclaredMethods()));
        return methods.stream()
            .filter(Objects::nonNull)
            .filter(method ->
                Arrays.stream(names)
                    .findFirst()
                    .map(name -> method.getName().equals(name))
                    .orElse(false))
            .findFirst()
            .map(MethodOf::new);
    }

    @NotNull
    @Override
    public <X> Optional<RefMethod> findMethodByReturnType(@NotNull final RefClass<X> type) {
        return this.findMethodByReturnType(type.getRealClass());
    }

    @NotNull
    @Override
    public Optional<RefMethod> findMethodByReturnType(@NotNull final Class<?> type) {
        final Collection<Method> methods = new ArrayList<>(Arrays.asList(this.clazz.getMethods()));
        methods.addAll(Arrays.asList(this.clazz.getDeclaredMethods()));
        return methods.stream()
            .filter(Objects::nonNull)
            .filter(method -> type.equals(method.getReturnType()))
            .findFirst()
            .map(MethodOf::new);
    }

    @NotNull
    @Override
    public Optional<RefConstructed<T>> getPrimitiveConstructor(@NotNull final Object... types) {
        return this.getConstructor0(true, types);
    }

    @NotNull
    @Override
    public Optional<RefConstructed<T>> getConstructor(@NotNull final Object... types) {
        return this.getConstructor0(false, types);
    }

    @NotNull
    @Override
    public Optional<RefConstructed<T>> findConstructor(final int number) {
        final Collection<Constructor<?>> constructors = new ArrayList<>(Arrays.asList(this.clazz.getConstructors()));
        constructors.addAll(Arrays.asList(this.clazz.getDeclaredConstructors()));
        return constructors.stream()
            .filter(Objects::nonNull)
            .filter(constructor -> constructor.getParameterTypes().length == number)
            .findFirst()
            .map(constructor -> new ConstructorOf<>((Constructor<T>) constructor));
    }

    @NotNull
    @Override
    public Optional<RefField> getField(@NotNull final String name) {
        try {
            return Optional.of(new FieldOf(this.clazz.getField(name)));
        } catch (final NoSuchFieldException ignored) {
            try {
                return Optional.of(new FieldOf(this.clazz.getDeclaredField(name)));
            } catch (final NoSuchFieldException e) {
                return Optional.empty();
            }
        }
    }

    @NotNull
    @Override
    public <X> Optional<RefField> findField(@NotNull final RefClass<X> type) {
        return this.findField(type.getRealClass());
    }

    @NotNull
    @Override
    public Optional<RefField> findField(final @NotNull Class<?> type) {
        final Collection<Field> fields = new ArrayList<>(Arrays.asList(this.clazz.getFields()));
        fields.addAll(Arrays.asList(this.clazz.getDeclaredFields()));
        return fields.stream()
            .filter(Objects::nonNull)
            .filter(field -> type.equals(field.getType()))
            .findFirst()
            .map(FieldOf::new);
    }

    @NotNull
    private Optional<RefMethod> getMethod0(@NotNull final String name, final boolean primitive,
                                           @NotNull final Object... types) {
        final RefParameterized<RefMethod> parameter = new ParameterizedOf<>(NoSuchMethodException::new, primitive,
            types);
        return parameter.apply(classes -> {
            try {
                return Optional.of(new MethodOf(this.clazz.getMethod(name, classes)));
            } catch (final NoSuchMethodException e) {
                return parameter.apply(declaredclasses -> {
                    try {
                        return Optional.of(new MethodOf(this.clazz.getDeclaredMethod(name, declaredclasses)));
                    } catch (final NoSuchMethodException noSuchMethodException) {
                        return Optional.empty();
                    }
                });
            }
        });
    }

    @NotNull
    private Optional<RefConstructed<T>> getConstructor0(final boolean primitive, @NotNull final Object... types) {
        final RefParameterized<RefConstructed<T>> parameter = new ParameterizedOf<>(NoSuchMethodException::new,
            primitive, types);
        return parameter.apply(classes -> {
            try {
                return Optional.of(new ConstructorOf<>(this.clazz.getConstructor(classes)));
            } catch (final NoSuchMethodException e) {
                return parameter.apply(declaredclasses -> {
                    try {
                        return Optional.of(new ConstructorOf<>(this.clazz.getDeclaredConstructor(declaredclasses)));
                    } catch (final NoSuchMethodException noSuchMethodException) {
                        return Optional.empty();
                    }
                });
            }
        });
    }

    @NotNull
    private Optional<RefMethod> findMethod0(final boolean primitive, @NotNull final Object... types) {
        final RefParameterized<RefMethod> parameter = new ParameterizedOf<>(Throwable::new, primitive, types);
        final Collection<Method> methods = new ArrayList<>(Arrays.asList(this.clazz.getMethods()));
        methods.addAll(Arrays.asList(this.clazz.getDeclaredMethods()));
        final Collection<Class<?>> classlist = new ArrayList<>();
        parameter.apply(classes -> {
            classlist.addAll(Arrays.asList(classes));
            return Optional.empty();
        });
        findMethod:
        for (final Method method : methods) {
            final Class<?>[] methodtypes = method.getParameterTypes();
            if (methodtypes.length != classlist.size()) {
                continue;
            }
            for (int index = 0; index < classlist.size(); index++) {
                if (!Arrays.equals(classlist.toArray(new Class<?>[0]), methodtypes)) {
                    continue findMethod;
                }
            }
            return Optional.of(new MethodOf(method));
        }
        return Optional.empty();
    }

}
