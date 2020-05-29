package io.github.portlek.reflection;

import java.util.Optional;
import java.util.function.Function;

public interface RefParameterized<T> extends Function<Function<Class<?>[], Optional<T>>, Optional<T>> {

}
