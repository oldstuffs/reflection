package io.github.portlek.reflection.parameter;

import io.github.portlek.reflection.RefParameter;
import org.cactoos.BiFunc;
import org.jetbrains.annotations.NotNull;

public class Parameter implements RefParameter {

    @NotNull
    private final Class[] classes;

    @NotNull
    private final Object[] objects;

    public Parameter(@NotNull final Class[] classes, @NotNull final Object[] objects) {
        this.classes = classes;
        this.objects = objects;
    }

    @NotNull
    private static RefParameter of(@NotNull final Object... args) {
        Class[] classes = new Class[args.length];
        int i = 0;

        for (Object e : args)
            classes[i++] = e.getClass();

        return new Parameter(classes, args);
    }

    @Override
    public Object apply(BiFunc<Class[], Object[], Object> func) throws Exception {
        return func.apply(classes, objects);
    }

}
