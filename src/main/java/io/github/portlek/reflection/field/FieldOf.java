package io.github.portlek.reflection.field;

import io.github.portlek.reflection.RefField;
import io.github.portlek.reflection.RefFieldExecuted;
import java.lang.reflect.Field;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class FieldOf implements RefField {

    @NotNull
    private final Field field;

    private final boolean isAccessible;

    public FieldOf(@NotNull final Field field) {
        this.field = field;
        this.isAccessible = field.isAccessible();
    }

    @NotNull
    @Override
    public RefFieldExecuted of(@NotNull final Object object) {
        return new FieldOf.FieldExecuted(object);
    }

    @Override
    public void set(@NotNull final Object value) {
        this.field.setAccessible(true);
        try {
            this.field.set(null, value);
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            this.field.setAccessible(this.isAccessible);
        }
    }

    @NotNull
    @Override
    public Optional<Object> get() {
        this.field.setAccessible(true);
        try {
            return Optional.ofNullable(this.field.get(null));
        } catch (final IllegalAccessException e) {
            return Optional.empty();
        } finally {
            this.field.setAccessible(this.isAccessible);
        }
    }

    private final class FieldExecuted implements RefFieldExecuted {

        @NotNull
        private final Object object;

        FieldExecuted(@NotNull final Object object) {
            this.object = object;
        }

        @Override
        public void set(@NotNull final Object value) {
            FieldOf.this.field.setAccessible(true);
            try {
                FieldOf.this.field.set(this.object, value);
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                FieldOf.this.field.setAccessible(FieldOf.this.isAccessible);
            }
        }

        @NotNull
        @Override
        public Optional<Object> get() {
            FieldOf.this.field.setAccessible(true);
            try {
                return Optional.ofNullable(FieldOf.this.field.get(this.object));
            } catch (final IllegalAccessException e) {
                return Optional.empty();
            } finally {
                FieldOf.this.field.setAccessible(FieldOf.this.isAccessible);
            }
        }

    }

}
