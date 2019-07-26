package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RefField {

    /**
     * apply fiend for object
     *
     * @param object applied object
     * @return RefFieldExecuted with getter and setter
     */
    @NotNull
    RefFieldExecuted of(@NotNull Object object);

    void set(@NotNull Object value);

    @Nullable
    Object get();

}
