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

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

/**
 * an interface for the objects which are able to annotate.
 */
public interface RefAnnotated {

  /**
   * gets the annotation from the given annotation class.
   *
   * @param annotationClass the annotation class to get.
   * @param <A> the annotation type.
   *
   * @return the given annotation class's instance if {@code this} have the given
   *   annotation class.
   */
  <A extends Annotation> Optional<A> getAnnotation(@NotNull Class<A> annotationClass);

  /**
   * gets the annotation from the given annotation class and if it's found runs the consumer.
   *
   * @param annotationClass the annotation class to get
   * @param consumer the consumer to run.
   * @param <A> the annotation type.
   */
  default <A extends Annotation> void getAnnotation(@NotNull final Class<A> annotationClass,
                                                    @NotNull final Consumer<A> consumer) {
    this.getAnnotation(annotationClass).ifPresent(consumer);
  }

  /**
   * checks if {@code this} has the given annotation.
   *
   * @param annotationClass the annotation class to check.
   * @param <A> the annotation type.
   *
   * @return {@code true} if {@code this} has the given annotation.
   */
  default <A extends Annotation> boolean hasAnnotation(@NotNull final Class<A> annotationClass) {
    return this.getAnnotation(annotationClass).isPresent();
  }
}
