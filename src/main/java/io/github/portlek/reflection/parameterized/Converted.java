package io.github.portlek.reflection.parameterized;

import io.github.portlek.reflection.RefClass;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public final class Converted implements Supplier<Class<?>[]> {

    private final boolean isPrimitive;

    private final Object[] objects;

    public Converted(final boolean isPrimitive, @NotNull final Object... objects) {
        this.isPrimitive = isPrimitive;
        this.objects = objects;
    }

    @NotNull
    @Override
    public Class<?>[] get() {
        final Class<?>[] classes = new Class[this.objects.length];
        final AtomicInteger atom = new AtomicInteger();
        try {
            for (final Object object : this.objects) {
                final Class<?> clazz;
                if (object instanceof RefClass) {
                    clazz = ((RefClass<?>) object).getRealClass();
                } else if (object instanceof Class) {
                    clazz = (Class<?>) object;
                } else {
                    clazz = object.getClass();
                }
                if (!this.isPrimitive) {
                    classes[atom.getAndIncrement()] = clazz;
                    continue;
                }
                classes[atom.getAndIncrement()] = new Primitive<>(clazz).get();
            }
        } catch (final Exception ignore) {
        }

        return classes;
    }

}
