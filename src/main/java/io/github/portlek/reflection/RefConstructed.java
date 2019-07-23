package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RefConstructed {

    /**
     * create new instance with constructor
     *
     * @param parameters parameters for constructor
     * @return new object
     */
    @Nullable
    Object create(@NotNull final Object... parameters);

}
