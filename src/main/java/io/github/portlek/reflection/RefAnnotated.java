package io.github.portlek.reflection;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public interface RefAnnotated {

    <A extends Annotation> Optional<A> annotation(@NotNull Class<A> annotationClass);

    default <A extends Annotation> void annotation(@NotNull final Class<A> annotationClass,
                                                   @NotNull final Consumer<A> consumer) {
        this.annotation(annotationClass).ifPresent(consumer);
    }

    default <A extends Annotation> boolean hasAnnotation(@NotNull final Class<A> annotationClass) {
        return this.annotation(annotationClass).isPresent();
    }

}
