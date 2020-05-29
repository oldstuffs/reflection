package io.github.portlek.reflection.parameterized;

import io.github.portlek.reflection.RefParameterized;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public final class ParameterizedOf<T> implements RefParameterized<T> {

    @NotNull
    private final Supplier<? extends Throwable> throwable;

    @NotNull
    private final Converted converted;

    public ParameterizedOf(@NotNull final Supplier<? extends Throwable> throwable, @NotNull final Converted converted) {
        this.throwable = throwable;
        this.converted = converted;
    }

    public ParameterizedOf(@NotNull final Supplier<? extends Throwable> throwable, final boolean primitive,
                           @NotNull final Object... objects) {
        this(throwable, new Converted(primitive, objects));
    }

    public ParameterizedOf(@NotNull final Supplier<? extends Throwable> throwable, @NotNull final Object... objects) {
        this(throwable, false, objects);
    }

    @Override
    public Optional<T> apply(final Function<Class<?>[], Optional<T>> func) {
        return func.apply(this.converted.get());
    }

}
