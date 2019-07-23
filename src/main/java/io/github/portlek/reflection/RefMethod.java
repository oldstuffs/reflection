package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
     * call static method
     *
     * @param parameters sent parameters
     * @return return value
     */
    @Nullable
    Object call(@NotNull final Object... parameters);

}