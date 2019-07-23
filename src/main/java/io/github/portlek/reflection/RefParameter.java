package io.github.portlek.reflection;

import org.cactoos.BiFunc;

public interface RefParameter {

    /**
     * Executes your command
     *
     * @param proc a consumer
     */
    Object apply(BiFunc<Class[], Object[], Object> proc) throws Exception;

}
