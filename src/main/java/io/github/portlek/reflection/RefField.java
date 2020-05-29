package io.github.portlek.reflection;

import java.util.Optional;
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
     * @return static field value with fallback
     */
    @NotNull
    Optional<Object> get();

}
