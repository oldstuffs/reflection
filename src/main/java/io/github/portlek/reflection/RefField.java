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

package io.github.portlek.reflection;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RefField extends RefFieldExecuted, RefAnnotated {

    /**
     * class type of the field
     *
     * @return a {@link Class} that's type of the field
     */
    @NotNull
    Class<?> type();

    /**
     * name of the field
     *
     * @return a {@link String} that's name of the field
     */
    @NotNull
    String name();

    /**
     * apply find for object
     *
     * @param object applied object
     * @return RefFieldExecuted with getter and setter
     */
    @NotNull
    RefFieldExecuted of(@Nullable Object object);

    /**
     * Sets static fields
     *
     * @param value object to set
     */
    @Override
    default void set(@NotNull final Object value) {
        this.of(null).set(value);
    }

    /**
     * Gets static fields
     *
     * @return static field value with fallback
     */
    @Override
    @NotNull
    default Optional<Object> get() {
        return this.of(null).get();
    }

}
