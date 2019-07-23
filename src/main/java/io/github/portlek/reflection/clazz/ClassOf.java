package io.github.portlek.reflection.clazz;

import io.github.portlek.reflection.*;
import io.github.portlek.reflection.constructor.ConstructorOf;
import io.github.portlek.reflection.field.FieldOf;
import io.github.portlek.reflection.mck.MckConstructed;
import io.github.portlek.reflection.mck.MckField;
import io.github.portlek.reflection.mck.MckMethod;
import io.github.portlek.reflection.method.MethodOf;
import io.github.portlek.reflection.parameter.ParameterOf;
import org.cactoos.iterable.Filtered;
import org.cactoos.list.Joined;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.FirstOf;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ClassOf implements RefClass {

    private static final Logger LOGGER = new LoggerOf(ClassOf.class);

    @NotNull
    private final Class<?> clazz;

    public ClassOf(@NotNull final Class<?> clazz) {
        this.clazz = clazz;
    }

    public ClassOf(@NotNull final Object object) {
        this(object.getClass());
    }

    public ClassOf(@NotNull final String className) throws ClassNotFoundException {
        this(Class.forName(className));
    }

    @NotNull
    public Class<?> getRealClass() {
        return clazz;
    }

    @Override
    public boolean isInstance(@NotNull final Object object) {
        return clazz.isInstance(object);
    }

    @NotNull
    @Override
    public RefMethod getMethod(@NotNull final String name, @NotNull final Object... types) {
        final RefParameter<RefMethod> parameter = new ParameterOf<>(types);

        try {
            try {
                return parameter.apply((classes, objects) ->
                    new MethodOf(clazz.getMethod(name, classes)));
            } catch (NoSuchMethodException ignored) {
                return parameter.apply((classes, objects) ->
                    new MethodOf(clazz.getDeclaredMethod(name, classes)));
            }
        } catch (Exception exception) {
            LOGGER.warning("getMethod(String, Object[]) -> \n"
                + exception.getMessage());
            return new MckMethod();
        }
    }

    @NotNull
    @Override
    public RefConstructed getConstructor(@NotNull final Object... types) {
        final RefParameter<RefConstructed> parameter = new ParameterOf<>(types);

        try {
            try {
                return parameter.apply((classes, objects) ->
                    new ConstructorOf(clazz.getConstructor(classes)));
            } catch (NoSuchMethodException ignored) {
                return parameter.apply((classes, objects) ->
                    new ConstructorOf(clazz.getDeclaredConstructor(classes)));
            }
        } catch (Exception exception) {
            LOGGER.warning("getConstructor(Object[]) -> \n"
                + exception.getMessage());
            return new MckConstructed();
        }
    }

    @NotNull
    @Override
    public RefMethod findMethod(@NotNull final Object... types) {
        final RefParameter<RefMethod> parameter = new ParameterOf<>(types);

        final List<Method> methods = new ListOf<>(
            new Joined<>(
                new ListOf<>(
                    clazz.getMethods()
                ),
                new ListOf<Method>(
                    clazz.getDeclaredMethods()
                )
            )
        );

        final List<Class> classList = new ArrayList<>();

        try {
            parameter.apply((classes, objects) -> {
                classList.addAll(new ListOf<>(classes));
                return new MckMethod();
            });
        } catch (Exception ignore) {
        }

        findMethod:
        for (Method method : methods) {
            final Class[] methodTypes = method.getParameterTypes();

            if (methodTypes.length != classList.size())
                continue;

            for (int i = 0; i < classList.size(); i++)
                if (!Arrays.equals(classList.toArray(new Class[0]), methodTypes))
                    continue findMethod;
                return new MethodOf(method);
        }

        LOGGER.warning("findMethod(Object[]) -> \n"
            + new NoSuchMethodException().getMessage());
        return new MckMethod();
    }

    @NotNull
    @Override
    public RefMethod findMethodByName(@NotNull final String... names) {
        try {
            return new MethodOf(
                new FirstOf<>(
                    input -> true,
                    new Filtered<>(
                        method -> {
                            if (method == null)
                                return false;

                            for (String name : names)
                                return method.getName().equals(name);

                            return false;
                        },
                        new Joined<>(
                            new ListOf<>(
                                clazz.getMethods()
                            ),
                            new ListOf<Method>(
                                clazz.getDeclaredMethods()
                            )
                        )
                    ),
                    () -> {
                        LOGGER.warning("findMethodByName(String[]) -> \n"
                            + new NoSuchMethodException().getMessage());
                        return null;
                    }
                ).value()
            );
        } catch (Exception exception) {
            LOGGER.warning("findMethodByName(String[]) -> \n"
                + exception.getMessage());
            return new MckMethod();
        }
    }

    @NotNull
    @Override
    public RefMethod findMethodByReturnType(@NotNull final RefClass type) {
        return findMethodByReturnType(type.getRealClass());
    }

    @NotNull
    @Override
    public RefMethod findMethodByReturnType(@NotNull final Class type) {
        try {
            return new MethodOf(
                new FirstOf<>(
                    method -> method != null && type.equals(method.getReturnType()),
                    new Joined<>(
                        new ListOf<>(
                            clazz.getMethods()
                        ),
                        new ListOf<Method>(
                            clazz.getDeclaredMethods()
                        )
                    ),
                    null
                ).value()
            );
        } catch (Exception exception) {
            LOGGER.warning("findMethodByReturnType(Class) -> \n"
                + exception.getMessage());
            return new MckMethod();
        }
    }

    @NotNull
    @Override
    public RefConstructed findConstructor(final int number) {
        try {
            return new ConstructorOf(
                new FirstOf<>(
                    constructor ->
                        constructor != null && constructor.getParameterTypes().length == number,
                    new Joined<>(
                        new ListOf<>(
                            clazz.getConstructors()
                        ),
                        new ListOf<Constructor>(
                            clazz.getDeclaredConstructors()
                        )
                    ),
                    null
                ).value()
            );
        } catch (Exception exception) {
            LOGGER.warning("findConstructor(int) -> \n"
                + exception.getMessage());
            return new MckConstructed();
        }
    }

    @NotNull
    @Override
    public RefField getField(@NotNull final String name) {
        try {
            try {
                return new FieldOf(clazz.getField(name));
            } catch (NoSuchFieldException ignored) {
                return new FieldOf(clazz.getDeclaredField(name));
            }
        } catch (Exception exception) {
            LOGGER.warning("getField(String) -> \n"
                + exception.getMessage());
            return new MckField();
        }
    }

    @NotNull
    @Override
    public RefField findField(@NotNull final RefClass type) {
        return findField(type.getRealClass());
    }

    @NotNull
    @Override
    public RefField findField(@NotNull final Class type) {
        try {
            return new FieldOf(
                new FirstOf<>(
                    field ->
                        field != null && type.equals(field.getType()),
                    new Joined<>(
                        new ListOf<>(
                            clazz.getFields()
                        ),
                        new ListOf<Field>(
                            clazz.getDeclaredFields()
                        )
                    ),
                    null
                ).value()
            );
        } catch (Exception exception) {
            LOGGER.warning("findField(Class) -> \n"
                + exception.getMessage());
            return new MckField();
        }
    }

}
