package io.github.portlek.reflection.constructor;

import io.github.portlek.reflection.RefConstructed;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public final class ConstructorOf<T> implements RefConstructed<T> {

    @NotNull
    private final Constructor<T> constructor;

    @Override
    public <A extends Annotation> Optional<A> annotation(@NotNull final Class<A> annotationClass) {
        return Optional.ofNullable(this.constructor.getDeclaredAnnotation(annotationClass));
    }

    @SneakyThrows
    @NotNull
    @Override
    public Optional<T> create(@NotNull final Object... parameters) {
        final boolean accessible = this.constructor.isAccessible();
        this.constructor.setAccessible(true);
        try {
            return Optional.of(this.constructor.newInstance(parameters));
        } finally {
            this.constructor.setAccessible(accessible);
        }
    }

}
