package io.github.portlek.reflection;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface RefConstructed<T> extends RefAnnotated {

    /**
     * create new instance with constructor
     *
     * @param parameters parameters for constructor
     * @return new object
     */
    @NotNull
    Optional<T> create(@NotNull Object... parameters);

}
