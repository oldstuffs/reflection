package io.github.portlek.reflection.parameter;

import io.github.portlek.reflection.RefParameter;
import org.cactoos.BiFunc;
import org.jetbrains.annotations.NotNull;

public class ParameterOf<T> implements RefParameter<T> {

    @NotNull
    private final Class[] classes;

    @NotNull
    private final Object[] objects;

    public ParameterOf(@NotNull final Class[] classes, @NotNull final Object[] objects) {
        this.classes = classes;
        this.objects = objects;
    }

    public ParameterOf(@NotNull final Object[] objects) {
        this(of(objects), objects);
    }

    @NotNull
    @Override
    public T apply(@NotNull BiFunc<Class[], Object[], T> func) throws Exception {
        return func.apply(classes, objects);
    }

    @NotNull
    private static Class[] of(@NotNull final Object... args) {
        Class[] classes = new Class[args.length];
        int i = 0;

        for (Object e : args)
            classes[i++] = e.getClass();

        return classes;
    }

}
