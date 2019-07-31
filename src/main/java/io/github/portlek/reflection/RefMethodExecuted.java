package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;

public interface RefMethodExecuted {

    /**
     * apply method for selected object
     *
     * @param fallback for null-safety
     * @param parameters sent parameters
     * @return return value with fallback
     */
    @NotNull
    Object call(@NotNull final Object fallback, @NotNull final Object... parameters);

}
