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
