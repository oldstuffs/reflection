package io.github.portlek.reflection.field;

import io.github.portlek.reflection.RefField;
import io.github.portlek.reflection.RefFieldExecuted;
import io.github.portlek.reflection.LoggerOf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class FieldOf implements RefField {

    private static final Logger LOGGER = new LoggerOf(
        FieldOf.class,
        FieldExecuted.class
    );

    private final Field field;
    private final boolean isAccessible;

    public FieldOf(Field field) {
        this.field = field;
        this.isAccessible = field.isAccessible();
    }

    @NotNull
    @Override
    public RefFieldExecuted of(@NotNull Object object) {
        return new FieldExecuted(object);
    }

    private class FieldExecuted implements RefFieldExecuted {

        private final Object object;

        FieldExecuted(Object object) {
            this.object = object;
        }

        @Override
        public void set(@NotNull Object value) {
            field.setAccessible(true);
            try {
                field.set(object, value);
            } catch (Exception exception) {
                LOGGER.warning("get(Object) -> " + exception.getMessage());
            } finally {
                field.setAccessible(isAccessible);
            }
        }

        @Nullable
        @Override
        public Object get() {
            field.setAccessible(true);
            try {
                return field.get(object);
            } catch (Exception exception) {
                LOGGER.warning("get(Object) -> " + exception.getMessage());
                return null;
            } finally {
                field.setAccessible(isAccessible);
            }
        }

    }

}
