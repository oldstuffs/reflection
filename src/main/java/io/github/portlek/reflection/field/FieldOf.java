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

package io.github.portlek.reflection.field;

import io.github.portlek.reflection.RefField;
import io.github.portlek.reflection.RefFieldExecuted;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Optional;
import java.util.logging.Level;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an implementation for {@link RefField}.
 */
@Log
@RequiredArgsConstructor
public final class FieldOf implements RefField {

  /**
   * the field.
   */
  @NotNull
  private final Field field;

  @Override
  public <A extends Annotation> Optional<A> getAnnotation(@NotNull final Class<A> annotationClass) {
    return Optional.ofNullable(this.field.getDeclaredAnnotation(annotationClass));
  }

  @NotNull
  @Override
  public String getName() {
    return this.field.getName();
  }

  @NotNull
  @Override
  public Field getRealField() {
    return this.field;
  }

  @NotNull
  @Override
  public Class<?> getType() {
    return this.field.getType();
  }

  @NotNull
  @Override
  public RefFieldExecuted of(@Nullable final Object object) {
    return new FieldOf.FieldExecuted(object);
  }

  @Override
  public boolean hasFinal() {
    return Modifier.isFinal(this.field.getModifiers());
  }

  @Override
  public boolean hasPrivate() {
    return Modifier.isPrivate(this.field.getModifiers());
  }

  @Override
  public boolean hasPublic() {
    return Modifier.isPublic(this.field.getModifiers());
  }

  @Override
  public boolean hasStatic() {
    return Modifier.isStatic(this.field.getModifiers());
  }

  /**
   * an implementation for {@link RefFieldExecuted}.
   */
  @RequiredArgsConstructor
  private final class FieldExecuted implements RefFieldExecuted {

    /**
     * the object.
     */
    @Nullable
    private final Object object;

    @NotNull
    @Override
    public Optional<Object> getValue() {
      final var accessible = FieldOf.this.field.isAccessible();
      try {
        FieldOf.this.field.setAccessible(true);
        return Optional.ofNullable(FieldOf.this.field.get(this.object));
      } catch (final IllegalAccessException exception) {
        FieldOf.log.log(Level.SEVERE, "FieldExecuted#getValue()", exception);
        return Optional.empty();
      } finally {
        FieldOf.this.field.setAccessible(accessible);
      }
    }

    @Override
    public void setValue(@NotNull final Object value) {
      final boolean accessible = FieldOf.this.field.isAccessible();
      try {
        FieldOf.this.field.setAccessible(true);
        FieldOf.this.field.set(this.object, value);
      } catch (final IllegalAccessException exception) {
        FieldOf.log.log(Level.SEVERE, "FieldExecuted#setValue(Object)", exception);
      } finally {
        FieldOf.this.field.setAccessible(accessible);
      }
    }
  }
}
