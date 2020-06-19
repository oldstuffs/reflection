package io.github.portlek.reflection;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RefField extends RefFieldExecuted, RefAnnotated {

    /**
     * class type of the field
     *
     * @return a {@link Class} that's type of the field
     */
    @NotNull
    Class<?> type();

    /**
     * apply find for object
     *
     * @param object applied object
     * @return RefFieldExecuted with getter and setter
     */
    @NotNull
    RefFieldExecuted of(@Nullable Object object);

    /**
     * Sets static fields
     *
     * @param value object to set
     */
    @Override
    default void set(@NotNull final Object value) {
        this.of(null).set(value);
    }

    /**
     * Gets static fields
     *
     * @return static field value with fallback
     */
    @Override
    @NotNull
    default Optional<Object> get() {
        return this.of(null).get();
    }

}
