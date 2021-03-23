/*
 * MIT License
 *
 * Copyright (c) 2021 Hasan Demirtaş
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

import io.github.portlek.reflection.RefParameterized;
import java.util.Optional;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;

/**
 * a class that allows you to use the given objects as parameter types.
 */
public final class ParameterizedOf<T> implements RefParameterized<T> {

  /**
   * the converted.
   */
  @NotNull
  private final Converted converted;

  /**
   * ctor.
   *
   * @param converted the converted.
   */
  public ParameterizedOf(@NotNull final Converted converted) {
    this.converted = converted;
  }

  /**
   * ctor.
   *
   * @param primitive the primitive.
   * @param objects the objects.
   */
  public ParameterizedOf(final boolean primitive, @NotNull final Object... objects) {
    this(new Converted(primitive, objects));
  }

  /**
   * ctor.
   *
   * @param objects the objects.
   */
  public ParameterizedOf(@NotNull final Object... objects) {
    this(false, objects);
  }

  @Override
  public Optional<T> apply(@NotNull final Function<Class<?>[], Optional<T>> func) {
    return func.apply(this.converted.get());
  }
}
