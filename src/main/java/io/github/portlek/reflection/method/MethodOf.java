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
