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

package io.github.portlek.reflection.parameterized;

import io.github.portlek.reflection.RefClass;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public final class Converted implements Supplier<Class<?>[]> {

    private final boolean isPrimitive;

    private final Object[] objects;

    @SneakyThrows
    @NotNull
    @Override
    public Class<?>[] get() {
        final Class<?>[] classes = new Class[this.objects.length];
        final AtomicInteger atom = new AtomicInteger();
        for (final Object object : this.objects) {
            final Class<?> clazz;
            if (object instanceof RefClass) {
                clazz = ((RefClass<?>) object).realClass();
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
        return classes;
    }

}
