package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RefFieldExecuted {

    void set(@NotNull Object value);

    @Nullable
    Object get();

}
