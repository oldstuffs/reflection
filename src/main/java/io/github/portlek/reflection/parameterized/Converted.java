package io.github.portlek.reflection.parameterized;

import io.github.portlek.reflection.RefClass;
import org.cactoos.Scalar;
import org.cactoos.scalar.And;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;

public class Converted implements Scalar<Class[]> {

    private final boolean isPrimitive;
    private final Object[] objects;

    public Converted(final boolean isPrimitive, @NotNull final Object... objects) {
        this.isPrimitive = isPrimitive;
        this.objects = objects;
    }

    @NotNull
    @Override
    public Class[] value() {
        Class[] classes = new Class[objects.length];
        final AtomicInteger i = new AtomicInteger();

        try {
            new And(
                object -> {
                    final Class clazz;

                    if (object instanceof RefClass)
                        clazz = ((RefClass)object).getRealClass();
                    else if (object instanceof Class)
                        clazz = (Class) object;
                    else
                        clazz = object.getClass();

                    if (!isPrimitive) {
                        classes[i.getAndIncrement()] = clazz;
                        return;
                    }

                    classes[i.getAndIncrement()] = new Primitive(clazz).value();
                },
                objects
            ).value();
        } catch (Exception ignore) {
        }

        return classes;
    }

}
