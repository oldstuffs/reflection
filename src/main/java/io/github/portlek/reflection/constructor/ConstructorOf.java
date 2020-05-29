package io.github.portlek.reflection.constructor;

import io.github.portlek.reflection.RefConstructed;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class ConstructorOf<T> implements RefConstructed<T> {

    @NotNull
    private final Constructor<T> constructor;

    private final boolean isAccessible;

    public ConstructorOf(@NotNull final Constructor<T> constructor) {
        this.constructor = constructor;
        this.isAccessible = constructor.isAccessible();
    }

    @NotNull
    @Override
    public Optional<T> create(@NotNull final Object... parameters) {
        this.constructor.setAccessible(true);
        try {
            return Optional.of(this.constructor.newInstance(parameters));
        } catch (final IllegalAccessException | InstantiationException | InvocationTargetException e) {
            return Optional.empty();
        } finally {
            this.constructor.setAccessible(this.isAccessible);
        }
    }

}
