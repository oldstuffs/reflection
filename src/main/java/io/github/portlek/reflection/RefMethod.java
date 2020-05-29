package io.github.portlek.reflection;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface RefMethod {

    /**
     * apply method to object
     *
     * @param object object to which the method is applied
     * @return RefMethodExecuted with method call(...)
     */
    @NotNull
    RefMethodExecuted of(@NotNull Object object);

    /**
     * Calls static method
     *
     * @param parameters sent parameters
     * @return return value with fallback
     */
    @NotNull
    Optional<Object> call(@NotNull Object... parameters);

}