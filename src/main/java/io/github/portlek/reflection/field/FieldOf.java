package io.github.portlek.reflection.field;

import io.github.portlek.reflection.RefField;
import io.github.portlek.reflection.RefFieldExecuted;
import io.github.portlek.reflection.LoggerOf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public class FieldOf implements RefField {

    private static final Logger LOGGER_FIELD_OF = new LoggerOf(
        FieldOf.class
    );
    private static final Logger LOGGER = new LoggerOf(
        FieldOf.class,
        FieldExecuted.class
    );

    @NotNull
    private final Field field;

    private final boolean isAccessible;

    public FieldOf(@NotNull final Field field) {
        this.field = field;
        this.isAccessible = field.isAccessible();
    }

    @Override
    public void set(@NotNull Object value) {
        field.setAccessible(true);
        try {
            field.set(null, value);
        } catch (Exception exception) {
            LOGGER_FIELD_OF.warning("set(Object) -> \n"
                + exception.getMessage());
        } finally {
            field.setAccessible(isAccessible);
        }
    }

    @Nullable
    @Override
    public Object get() {
        field.setAccessible(true);
        try {
            return field.get(null);
        } catch (Exception exception) {
            LOGGER_FIELD_OF.warning("get() -> \n"
                + exception.getMessage());
            return null;
        } finally {
            field.setAccessible(isAccessible);
        }
    }

    @NotNull
    @Override
    public RefFieldExecuted of(@NotNull final Object object) {
        return new FieldExecuted(object);
    }

    private class FieldExecuted implements RefFieldExecuted {

        @NotNull
        private final Object object;

        FieldExecuted(@NotNull final Object object) {
            this.object = object;
        }

        @Override
        public void set(@NotNull Object value) {
            field.setAccessible(true);
            try {
                field.set(object, value);
            } catch (Exception exception) {
                LOGGER.warning("set(Object) -> \n"
                    + exception.getMessage());
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
                LOGGER.warning("get() -> \n"
                    + exception.getMessage());
                return null;
            } finally {
                field.setAccessible(isAccessible);
            }
        }

    }

}
