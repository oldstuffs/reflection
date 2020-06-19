package io.github.portlek.reflection.method;

import io.github.portlek.reflection.RefMethod;
import io.github.portlek.reflection.RefMethodExecuted;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public final class MethodOf implements RefMethod {

    @NotNull
    private final Method method;

    @NotNull
    @Override
    public RefMethodExecuted of(@Nullable final Object object) {
        return new MethodOf.MethodExecuted(this.method, object);
    }

    @Override
    public <A extends Annotation> Optional<A> annotation(@NotNull final Class<A> annotationClass) {
        return Optional.ofNullable(this.method.getDeclaredAnnotation(annotationClass));
    }

    @RequiredArgsConstructor
    private static final class MethodExecuted implements RefMethodExecuted {

        @NotNull
        private final Method method;

        @Nullable
        private final Object object;

        @SneakyThrows
        @NotNull
        @Override
        public Optional<Object> call(@NotNull final Object... parameters) {
            final boolean accessible = this.method.isAccessible();
            this.method.setAccessible(true);
            try {
                return Optional.ofNullable(this.method.invoke(this.object, parameters));
            } finally {
                this.method.setAccessible(accessible);
            }
        }

    }

}
