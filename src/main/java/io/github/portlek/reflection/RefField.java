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

import java.lang.reflect.Field;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine {@link Field}.
 */
public interface RefField extends RefFieldExecuted, RefAnnotated {

  /**
   * gets name of the field.
   *
   * @return name of the field.
   */
  @NotNull
  String getName();

  /**
   * obtains the real field.
   *
   * @return the real field.
   */
  @NotNull
  Field getRealField();

  /**
   * gets the type of the field.
   *
   * @return a {@link Class} that's type of the field
   */
  @NotNull
  Class<?> getType();

  /**
   * gets the field's object as a static.
   *
   * @return static field value.
   */
  @Override
  @NotNull
  default Optional<Object> getValue() {
    return this.of(null).getValue();
  }

  /**
   * sets the given object to the static field.
   *
   * @param value object to set.
   */
  @Override
  default void setValue(@NotNull final Object value) {
    this.of(null).setValue(value);
  }

  /**
   * checks if the field has {@code final} modifier.
   *
   * @return {@code true} if the field has {@code final} modifier.
   */
  boolean hasFinal();

  /**
   * checks if the field has {@code private} modifier.
   *
   * @return {@code true} if the field has {@code private} modifier.
   */
  boolean hasPrivate();

  /**
   * checks if the field has {@code public} modifier.
   *
   * @return {@code true} if the field has {@code public} modifier.
   */
  boolean hasPublic();

  /**
   * checks if the field has {@code static} modifier.
   *
   * @return {@code true} if the field has {@code static} modifier.
   */
  boolean hasStatic();

  /**
   * applies the given object to create a {@link RefFieldExecuted} object.
   *
   * @param object the object to apply.
   *
   * @return a {@link RefFieldExecuted} object.
   */
  @NotNull
  RefFieldExecuted of(@Nullable Object object);
}
