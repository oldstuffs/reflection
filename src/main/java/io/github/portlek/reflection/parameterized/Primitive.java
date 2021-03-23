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

import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * a class that converts the given class into the its primitive.
 *
 * @param <T> the class type.
 */
public final class Primitive<T> implements Supplier<Class<T>> {

  /**
   * the class
   */
  @NotNull
  private final Class<T> clazz;

  /**
   * ctor.
   *
   * @param clazz the class.
   */
  public Primitive(@NotNull final Class<T> clazz) {
    this.clazz = clazz;
  }

  @SuppressWarnings("unchecked")
  @NotNull
  @Override
  public Class<T> get() {
    switch (this.clazz.getName()) {
      case "java.lang.Integer":
        return (Class<T>) Integer.TYPE;
      case "java.lang.Float":
        return (Class<T>) Float.TYPE;
      case "java.lang.Short":
        return (Class<T>) Short.TYPE;
      case "java.lang.Character":
        return (Class<T>) Character.TYPE;
      case "java.lang.Boolean":
        return (Class<T>) Boolean.TYPE;
      case "java.lang.Byte":
        return (Class<T>) Byte.TYPE;
      case "java.lang.Long":
        return (Class<T>) Long.TYPE;
      case "java.lang.Void":
        return (Class<T>) Void.TYPE;
      case "java.lang.Double":
        return (Class<T>) Double.TYPE;
      default:
        return this.clazz;
    }
  }
}
