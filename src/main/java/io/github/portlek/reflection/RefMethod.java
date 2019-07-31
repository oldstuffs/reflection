package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;

public interface RefMethod {

    /**
     * apply method to object
     *
     * @param object object to which the method is applied
     * @return RefMethodExecuted with method call(...)
     */
    @NotNull
    RefMethodExecuted of(@NotNull final Object object);

    /**
     * Calls static method
     *
     * @param fallback for null-safety
     * @param parameters sent parameters
     * @return return value with fallback
     */
    @NotNull
    Object call(@NotNull final Object fallback, @NotNull final Object... parameters);

}