package io.github.portlek.reflection.constructor;

import io.github.portlek.reflection.LoggerOf;
import io.github.portlek.reflection.RefConstructed;
import io.github.portlek.reflection.RefParameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public class ConstructorOf implements RefConstructed {

    private static final Logger LOGGER = new LoggerOf(ConstructorOf.class);

    @NotNull
    private final Constructor constructor;

    public ConstructorOf(@NotNull final Constructor constructor) {
        this.constructor = constructor;
    }

    @Nullable
    @Override
    public Object create(@NotNull final RefParameter parameters) {
        try {
            return parameters.apply((first, second) -> constructor.newInstance(second));
        } catch (Exception e) {
            LOGGER.warning("create(Object[]) -> " + e.getMessage());
            return null;
        }
    }

}
