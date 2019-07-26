package io.github.portlek.reflection.parameterized;

import io.github.portlek.reflection.RefParameterized;
import org.cactoos.Func;
import org.jetbrains.annotations.NotNull;

public class ParameterizedOf<T> implements RefParameterized<T> {

    @NotNull
    private final Converted converted;

    public ParameterizedOf(@NotNull Converted converted) {
        this.converted = converted;
    }

    public ParameterizedOf(final boolean primitive, @NotNull final Object... objects) {
        this(new Converted(primitive, objects));
    }

    public ParameterizedOf(@NotNull final Object... objects) {
        this(false, objects);
    }

    @NotNull
    @Override
    public T apply(@NotNull Func<Class[], T> func) throws Exception {
        return func.apply(converted.value());
    }

}
