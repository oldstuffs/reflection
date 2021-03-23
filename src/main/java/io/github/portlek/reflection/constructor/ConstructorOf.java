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

package io.github.portlek.reflection.constructor;

import io.github.portlek.reflection.RefConstructed;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

/**
 * an implementation for {@link RefConstructed}.
 *
 * @param <T> the result instance's type.
 */
@Log
public final class ConstructorOf<T> implements RefConstructed<T> {

  /**
   * the constructor.
   */
  @NotNull
  private final Constructor<T> constructor;

  /**
   * ctor.
   *
   * @param constructor the constructor.
   */
  public ConstructorOf(@NotNull final Constructor<T> constructor) {
    this.constructor = constructor;
  }

  @NotNull
  @Override
  public Optional<T> create(@NotNull final Object... parameters) {
    final var accessible = this.constructor.isAccessible();
    try {
      this.constructor.setAccessible(true);
      return Optional.of(this.constructor.newInstance(parameters));
    } catch (final IllegalAccessException | InstantiationException | InvocationTargetException exception) {
      ConstructorOf.log.log(Level.SEVERE, "ConstructorOf#create(Object[])", exception);
      return Optional.empty();
    } finally {
      this.constructor.setAccessible(accessible);
    }
  }

  @Override
  public <A extends Annotation> Optional<A> getAnnotation(@NotNull final Class<A> annotationClass) {
    return Optional.ofNullable(this.constructor.getDeclaredAnnotation(annotationClass));
  }
}
