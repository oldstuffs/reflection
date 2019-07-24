package io.github.portlek.reflection.constructor;

import io.github.portlek.reflection.LoggerOf;
import io.github.portlek.reflection.RefConstructed;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public class ConstructorOf implements RefConstructed {

    private static final Logger LOGGER = new LoggerOf(ConstructorOf.class);

    @NotNull
    private final Constructor constructor;

    private final boolean isAccessible;

    public ConstructorOf(@NotNull final Constructor constructor) {
        this.constructor = constructor;
        this.isAccessible = constructor.isAccessible();
    }

    @Nullable
    @Override
    public Object create(@NotNull final Object... parameters) {
        constructor.setAccessible(true);
        try {
            return constructor.newInstance(parameters);
        } catch (Exception e) {
            LOGGER.warning("create(Object[]) -> \n"
                + e.getMessage());
            return null;
        } finally {
            constructor.setAccessible(isAccessible);
        }
    }

}
