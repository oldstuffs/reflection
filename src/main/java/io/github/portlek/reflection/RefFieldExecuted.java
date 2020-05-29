package io.github.portlek.reflection;

import java.util.Optional;
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
     * @return field value with fallback
     */
    @NotNull
    Optional<Object> get();

}
