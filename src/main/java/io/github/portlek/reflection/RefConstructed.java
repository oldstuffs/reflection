package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;

public interface RefConstructed {

    /**
     * create new instance with constructor
     *
     * @param fallback   fallback object for null-safety
     * @param parameters parameters for constructor
     * @return new object
     */
    @NotNull
    Object create(@NotNull final Object fallback, @NotNull final Object... parameters);

}
