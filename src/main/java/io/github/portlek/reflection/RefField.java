package io.github.portlek.reflection;

import org.jetbrains.annotations.NotNull;

public interface RefField {

    /**
     * apply fiend for object
     *
     * @param object applied object
     * @return RefFieldExecuted with getter and setter
     */
    @NotNull
    RefFieldExecuted of(@NotNull Object object);

}
