package io.github.portlek.reflection.parameter;

import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

public class Primitive implements Scalar<Class> {

    @NotNull
    private final Class clazz;

    public Primitive(@NotNull final Class clazz) {
        this.clazz = clazz;
    }

    @NotNull
    @Override
    public Class value() {
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
