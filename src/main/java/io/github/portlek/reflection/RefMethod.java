package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RefMethod {

    /**
     * apply method to object
     *
     * @param object object to which the method is applied
     * @return RefExecutor with method call(...)
     */
    @NotNull
    RefMethodExecuted of(@NotNull final Object object);

    /**
     * call static method
     *
     * @param objects sent parameters
     * @return return value
     */
    @Nullable
    Object call(@NotNull final Object... objects);

}