package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;

public interface RefField {

    /**
     * apply find for object
     *
     * @param object applied object
     * @return RefFieldExecuted with getter and setter
     */
    @NotNull
    RefFieldExecuted of(@NotNull Object object);

    /**
     * Sets static fields
     *
     * @param value object to set
     */
    void set(@NotNull Object value);

    /**
     * Gets static fields
     *
     * @param fallback for null-safety
     * @return static field value with fallback
     */
    @NotNull
    Object get(@NotNull final Object fallback);

}
