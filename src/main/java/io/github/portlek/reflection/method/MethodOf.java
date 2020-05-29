package io.github.portlek.reflection.method;

import io.github.portlek.reflection.RefMethod;
import io.github.portlek.reflection.RefMethodExecuted;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class MethodOf implements RefMethod {

    @NotNull
    private final Method method;

    private final boolean isAccessible;

    public MethodOf(@NotNull final Method method) {
        this.method = method;
        this.isAccessible = this.method.isAccessible();
    }

    @NotNull
    @Override
    public RefMethodExecuted of(@NotNull final Object object) {
        return new MethodOf.MethodExecuted(object);
    }

    @NotNull
    @Override
    public Optional<Object> call(@NotNull final Object... parameters) {
        this.method.setAccessible(true);
        try {
            return Optional.ofNullable(this.method.invoke(null, parameters));
        } catch (final IllegalAccessException | InvocationTargetException e) {
            return Optional.empty();
        } finally {
            this.method.setAccessible(this.isAccessible);
        }
    }

    private final class MethodExecuted implements RefMethodExecuted {

        @NotNull
        private final Object object;

        MethodExecuted(@NotNull final Object object) {
            this.object = object;
        }

        @NotNull
        @Override
        public Optional<Object> call(@NotNull final Object... parameters) {
            MethodOf.this.method.setAccessible(true);
            try {
                return Optional.ofNullable(MethodOf.this.method.invoke(this.object, parameters));
            } catch (final IllegalAccessException | InvocationTargetException e) {
                return Optional.empty();
            } finally {
                MethodOf.this.method.setAccessible(MethodOf.this.isAccessible);
            }
        }

    }

}
