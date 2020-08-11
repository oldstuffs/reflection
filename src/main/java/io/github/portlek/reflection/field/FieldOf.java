/*
 * MIT License
 *
 * Copyright (c) 2020 Hasan Demirtaş
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.portlek.reflection.field;

import io.github.portlek.reflection.RefField;
import io.github.portlek.reflection.RefFieldExecuted;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public final class FieldOf implements RefField {

    @NotNull
    private final Field field;

    @NotNull
    @Override
    public Class<?> type() {
        return this.field.getType();
    }

    @NotNull
    @Override
    public String name() {
        return this.field.getName();
    }

    @NotNull
    @Override
    public RefFieldExecuted of(@Nullable final Object object) {
        return new FieldOf.FieldExecuted(this.field, object);
    }

    @Override
    public <A extends Annotation> Optional<A> annotation(@NotNull final Class<A> annotationClass) {
        return Optional.ofNullable(this.field.getDeclaredAnnotation(annotationClass));
    }

    @RequiredArgsConstructor
    private static final class FieldExecuted implements RefFieldExecuted {

        @NotNull
        private final Field field;

        @Nullable
        private final Object object;

        @SneakyThrows
        @Override
        public void set(@NotNull final Object value) {
            final boolean accessible = this.field.isAccessible();
            this.field.setAccessible(true);
            this.field.set(this.object, value);
            this.field.setAccessible(accessible);
        }

        @SneakyThrows
        @NotNull
        @Override
        public Optional<Object> get() {
            final boolean accessible = this.field.isAccessible();
            this.field.setAccessible(true);
            try {
                return Optional.ofNullable(this.field.get(this.object));
            } finally {
                this.field.setAccessible(accessible);
            }
        }

    }

}
