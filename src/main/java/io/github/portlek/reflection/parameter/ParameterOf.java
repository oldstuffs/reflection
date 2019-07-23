package io.github.portlek.reflection.parameter;

import io.github.portlek.reflection.RefParameter;
import org.cactoos.BiFunc;
import org.jetbrains.annotations.NotNull;

public class ParameterOf implements RefParameter {

    @NotNull
    private final Class[] classes;

    @NotNull
    private final Object[] objects;

    public ParameterOf(@NotNull final Class[] classes, @NotNull final Object[] objects) {
        this.classes = classes;
        this.objects = objects;
    }

    @Override
    public Object apply(BiFunc<Class[], Object[], Object> func) throws Exception {
        return func.apply(classes, objects);
    }

}
