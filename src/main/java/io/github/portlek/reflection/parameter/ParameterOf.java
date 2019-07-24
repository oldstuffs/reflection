package io.github.portlek.reflection.parameter;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.RefParameter;
import org.cactoos.Func;
import org.jetbrains.annotations.NotNull;

public class ParameterOf<T> implements RefParameter<T> {

    @NotNull
    private final Class[] classes;

    public ParameterOf(@NotNull final Class[] classes) {
        this.classes = classes;
    }

    public ParameterOf(final boolean primitive, @NotNull final Object... objects) {
        this(of(primitive, objects));
    }

    public ParameterOf(@NotNull final Object... objects) {
        this(false, objects);
    }

    @NotNull
    @Override
    public T apply(@NotNull Func<Class[], T> func) throws Exception {
        return func.apply(classes);
    }

    @NotNull
    private static Class[] of(final boolean primitive, @NotNull final Object... args) {
        Class[] classes = new Class[args.length];
        int i = 0;

        for (Object arg : args) {
            final Class clazz;

            if (arg instanceof RefClass)
                clazz = ((RefClass)arg).getRealClass();
            else if (arg instanceof Class)
                clazz = (Class) arg;
            else
                clazz = arg.getClass();

            if (!primitive) {
                classes[i++] = clazz;
                continue;
            }

            classes[i++] = primitive(clazz);
        }

        return classes;
    }

    @NotNull
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
                return clazz;
        }
    }

}
