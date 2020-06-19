package io.github.portlek.reflection;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RefMethod extends RefMethodExecuted, RefAnnotated {

    /**
     * apply method to object
     *
     * @param object object to which the method is applied
     * @return RefMethodExecuted with method call(...)
     */
    @NotNull
    RefMethodExecuted of(@Nullable Object object);

    /**
     * Calls static method
     *
     * @param parameters sent parameters
     * @return return value with fallback
     */
    @Override
    @NotNull
    default Optional<Object> call(@NotNull final Object... parameters) {
        return this.of(null).call(parameters);
    }

}