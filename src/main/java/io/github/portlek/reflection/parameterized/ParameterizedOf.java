package io.github.portlek.reflection.parameterized;

import io.github.portlek.reflection.RefParameterized;
import java.util.Optional;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public final class ParameterizedOf<T> implements RefParameterized<T> {

    @NotNull
    private final Converted converted;

    public ParameterizedOf(final boolean primitive,
                           @NotNull final Object... objects) {
        this(new Converted(primitive, objects));
    }

    public ParameterizedOf(@NotNull final Object... objects) {
        this(false, objects);
    }

    @Override
    public Optional<T> apply(final Function<Class<?>[], Optional<T>> func) {
        return func.apply(this.converted.get());
    }

}
