package io.github.portlek.reflection.mck;

import io.github.portlek.reflection.RefConstructed;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MckConstructed implements RefConstructed {
    @Nullable
    @Override
    public Object create(@NotNull Object... parameters) {
        return null;
    }
}
