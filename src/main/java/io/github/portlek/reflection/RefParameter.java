package io.github.portlek.reflection;

import org.cactoos.Func;
import org.jetbrains.annotations.NotNull;

/**
 * @param <T> result type
 */
public interface RefParameter<T> {

    /**
     * Executes your command
     *
     * @param func a function of Class[]
     */
    T apply(@NotNull final Func<Class[], T> func) throws Exception;

}
