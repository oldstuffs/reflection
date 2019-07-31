package io.github.portlek.reflection.mck;

import io.github.portlek.reflection.RefConstructed;
import org.jetbrains.annotations.NotNull;

public class MckConstructed implements RefConstructed {
    @NotNull
    @Override
    public Object create(@NotNull final Object fallback, @NotNull Object... parameters) {
        return fallback;
    }
}
