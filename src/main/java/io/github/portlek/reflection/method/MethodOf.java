package io.github.portlek.reflection.method;

import io.github.portlek.reflection.LoggerOf;
import io.github.portlek.reflection.RefMethod;
import io.github.portlek.reflection.RefMethodExecuted;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.logging.Logger;

public class MethodOf implements RefMethod {

    private static final Logger LOGGER_METHOD_OF = new LoggerOf(MethodOf.class);
    private static final Logger LOGGER = new LoggerOf(
        MethodOf.class,
        MethodExecuted.class
    );

    @NotNull
    private final Method method;

    private final boolean isAccessible;

    public MethodOf(@NotNull final Method method) {
        this.method = method;
        this.isAccessible = method.isAccessible();
    }

    @NotNull
    @Override
    public RefMethodExecuted of(@NotNull final Object object) {
        return new MethodExecuted(object);
    }

    @NotNull
    @Override
    public Object call(@NotNull final Object fallback, @NotNull final Object... parameters) {
        method.setAccessible(true);
        try {
            return method.invoke(null, parameters);
        } catch (Exception e) {
            LOGGER_METHOD_OF.warning("call(Object[]) -> \n"
                + e.toString());
            return fallback;
        } finally {
            method.setAccessible(isAccessible);
        }
    }

    private class MethodExecuted implements RefMethodExecuted {

        @NotNull
        private final Object object;

        MethodExecuted(@NotNull final Object object) {
            this.object = object;
        }

        @NotNull
        @Override
        public Object call(@NotNull final Object fallback, @NotNull final Object... parameters) {
            method.setAccessible(true);
            try {
                return method.invoke(object, parameters) == null
                    ? fallback
                    : method.invoke(object, parameters);
            } catch (Exception e) {
                LOGGER.warning("call(Object[]) -> \n"
                    + e.toString());
                return fallback;
            } finally {
                method.setAccessible(isAccessible);
            }
        }

    }

}
