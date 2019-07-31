package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;

public interface RefFieldExecuted {

    /**
     * Sets field
     *
     * @param value object to set
     */
    void set(@NotNull Object value);

    /**
     * Gets field
     *
     * @param fallback for null-safety
     * @return field value with fallback
     */
    @NotNull
    Object get(@NotNull final Object fallback);

}
