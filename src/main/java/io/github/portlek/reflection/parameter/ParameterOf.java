package io.github.portlek.reflection.parameter;

import io.github.portlek.reflection.RefParameter;
import org.cactoos.BiFunc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ParameterOf<T> implements RefParameter<T> {

    @NotNull
    private final Class[] classes;

    @NotNull
    private final Object[] objects;

    public ParameterOf(@NotNull final Class[] classes, @NotNull final Object[] objects) {
        this.classes = classes;
        this.objects = objects;
    }

    public ParameterOf(final boolean primitive, @NotNull final Object[] objects) {
        this(of(primitive, objects), objects);
    }

    public ParameterOf(@NotNull final Object[] objects) {
        this(false, objects);
    }

    @NotNull
    @Override
    public T apply(@NotNull BiFunc<Class[], Object[], T> func) throws Exception {
        return func.apply(classes, objects);
    }

    @NotNull
    private static Class[] of(final boolean primitive, @NotNull final Object... args) {
        @NotNull Class[] classes = new Class[args.length];
        int i = 0;

        for (Object e : args) {
            if (!primitive) {
                classes[i++] = e.getClass();
                continue;
            }

            if (!e.getClass().isPrimitive()) {
                classes[i++] = e.getClass();
                continue;
            }

            Class primitiveClass = primitive(e.getClass());

            if (primitiveClass == null) {
                classes[i++] = e.getClass();
                continue;
            }

            classes[i++] = primitiveClass;
        }

        return classes;
    }

    @Nullable
    private static Class primitive(Class clazz) {
        switch (clazz.getName()) {
            case "java.lang.Integer":
                return Integer.TYPE;
            case "java.lang.Float":
                return Float.TYPE;
            case "java.lang.Short":
                return Short.TYPE;
            case "java.lang.Character":
                return Character.TYPE;
            case "java.lang.Boolean":
                return Boolean.TYPE;
            case "java.lang.Byte":
                return Byte.TYPE;
            case "java.lang.Long":
                return Long.TYPE;
            case "java.lang.Void":
                return Void.TYPE;
            case "java.lang.Double":
                return Double.TYPE;
            default:
                return null;
        }
    }

}
