package io.github.portlek.reflection.parameter;

import io.github.portlek.reflection.RefClass;
import org.cactoos.Scalar;
import org.jetbrains.annotations.NotNull;

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
        int i = 0;

        for (Object arg : objects) {
            final Class clazz;

            if (arg instanceof RefClass)
                clazz = ((RefClass)arg).getRealClass();
            else if (arg instanceof Class)
                clazz = (Class) arg;
            else
                clazz = arg.getClass();

            if (!isPrimitive) {
                classes[i++] = clazz;
                continue;
            }

            classes[i++] = new Primitive(clazz).value();
        }

        return classes;
    }

}
