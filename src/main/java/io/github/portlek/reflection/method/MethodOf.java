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

package io.github.portlek.reflection.method;

import io.github.portlek.reflection.RefMethod;
import io.github.portlek.reflection.RefMethodExecuted;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an implementation for {@link RefMethod}.
 */
public final class MethodOf implements RefMethod {

  /**
   * the method.
   */
  @NotNull
  private final Method method;

  /**
   * ctor.
   *
   * @param method the method.
   */
  public MethodOf(@NotNull final Method method) {
    this.method = method;
  }

  @Override
  public <A extends Annotation> Optional<A> getAnnotation(@NotNull final Class<A> annotationClass) {
    return Optional.ofNullable(this.method.getDeclaredAnnotation(annotationClass));
  }

  @NotNull
  @Override
  public RefMethodExecuted of(@Nullable final Object object) {
    return new MethodOf.MethodExecuted(object);
  }

  /**
   * an implementation for {@link RefMethodExecuted}.
   */
  private final class MethodExecuted implements RefMethodExecuted {

    /**
     * the object.
     */
    @Nullable
    private final Object object;

    /**
     * ctor.
     *
     * @param object the object.
     */
    MethodExecuted(@Nullable final Object object) {
      this.object = object;
    }

    @NotNull
    @Override
    public Optional<Object> call(@NotNull final Object... parameters) {
      final boolean accessible = MethodOf.this.method.isAccessible();
      try {
        MethodOf.this.method.setAccessible(true);
        return Optional.ofNullable(MethodOf.this.method.invoke(this.object, parameters));
      } catch (final IllegalAccessException | InvocationTargetException exception) {
        throw new IllegalStateException(exception);
      } finally {
        MethodOf.this.method.setAccessible(accessible);
      }
    }
  }
}
