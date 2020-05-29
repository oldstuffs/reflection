package io.github.portlek.reflection;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface RefMethodExecuted {

    /**
     * apply method for selected object
     *
     * @param parameters sent parameters
     * @return return value with fallback
     */
    @NotNull
    Optional<Object> call(@NotNull Object... parameters);

}
