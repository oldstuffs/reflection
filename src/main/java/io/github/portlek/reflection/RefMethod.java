package io.github.portlek.reflection;

import org.jetbrains.annotations.Nullable;

public interface RefMethod {

    /**
     * call static method
     *
     * @param objects sent parameters
     * @return return value
     */
    @Nullable
    Object call(Object... objects);

}