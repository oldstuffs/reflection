package io.github.portlek.reflection.parameterized;

import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public final class Primitive<T> implements Supplier<Class<T>> {

    @NotNull
    private final Class<T> clazz;

    public Primitive(@NotNull final Class<T> clazz) {
        this.clazz = clazz;
    }

    @NotNull
    @Override
    public Class<T> get() {
        switch (this.clazz.getName()) {
            case "java.lang.Integer":
                return (Class<T>) Integer.TYPE;
            case "java.lang.Float":
                return (Class<T>) Float.TYPE;
            case "java.lang.Short":
                return (Class<T>) Short.TYPE;
            case "java.lang.Character":
                return (Class<T>) Character.TYPE;
            case "java.lang.Boolean":
                return (Class<T>) Boolean.TYPE;
            case "java.lang.Byte":
                return (Class<T>) Byte.TYPE;
            case "java.lang.Long":
                return (Class<T>) Long.TYPE;
            case "java.lang.Void":
                return (Class<T>) Void.TYPE;
            case "java.lang.Double":
                return (Class<T>) Double.TYPE;
            default:
                return this.clazz;
        }
    }

}
