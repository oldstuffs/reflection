package io.github.portlek.reflection;

import org.cactoos.BiFunc;
import org.jetbrains.annotations.NotNull;

/**
 * @param <T> result type
 */
public interface RefParameter<T> {

    /**
     * Executes your command
     *
     * @param proc a consumer
     */
    T apply(@NotNull final BiFunc<Class[], Object[], T> proc) throws Exception;

}
