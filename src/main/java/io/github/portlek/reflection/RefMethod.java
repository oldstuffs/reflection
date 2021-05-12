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

package io.github.portlek.reflection;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine {@link java.lang.reflect.Method}.
 */
public interface RefMethod extends RefMethodExecuted, RefAnnotated, RefModifiable {

  /**
   * calls the method with the given parameters as a static.
   *
   * @param parameters the parameters to call.
   *
   * @return value of the method.
   */
  @Override
  @NotNull
  default Optional<Object> call(@NotNull final Object... parameters) {
    return this.of(null).call(parameters);
  }

  /**
   * obtains the method's name.
   *
   * @return method's name.
   */
  @NotNull
  String getName();

  /**
   * obtains the parameter types of the method.
   *
   * @return parameter types.
   */
  @NotNull
  Class<?>[] getParameterTypes();

  /**
   * obtains the return type of the method.
   *
   * @return return type.
   */
  @NotNull
  Class<?> getReturnType();

  /**
   * applies the given object to create a {@link RefMethodExecuted} object.
   *
   * @param object the object to apply.
   *
   * @return a {@link RefMethodExecuted} object.
   */
  @NotNull
  RefMethodExecuted of(@Nullable Object object);
}
