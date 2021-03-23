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

import io.github.portlek.reflection.RefClass;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * a class that converts the given objects into the {@link Class}.
 */
public final class Converted implements Supplier<Class<?>[]> {

  /**
   * the is primitive.
   */
  private final boolean isPrimitive;

  /**
   * the objects.
   */
  @NotNull
  private final Object[] objects;

  /**
   * ctor.
   *
   * @param isPrimitive the is primitive.
   * @param objects the objects.
   */
  public Converted(final boolean isPrimitive, @NotNull final Object[] objects) {
    this.isPrimitive = isPrimitive;
    this.objects = objects.clone();
  }

  @NotNull
  @Override
  public Class<?>[] get() {
    final Class<?>[] classes = new Class[this.objects.length];
    for (int index = 0; index < this.objects.length; index++) {
      final Object object = this.objects[index];
      final Class<?> clazz;
      if (object instanceof RefClass) {
        clazz = ((RefClass<?>) object).getRealClass();
      } else if (object instanceof Class) {
        clazz = (Class<?>) object;
      } else {
        clazz = object.getClass();
      }
      if (this.isPrimitive) {
        classes[index] = new Primitive<>(clazz).get();
      } else {
        classes[index] = clazz;
      }
    }
    return classes;
  }
}
