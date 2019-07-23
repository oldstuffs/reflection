package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RefMethodExecuted {

    /**
     * apply method for selected object
     *
     * @param parameters sent parameters
     * @return return value
     */
    @Nullable
    Object call(@NotNull final Object... parameters);

}
