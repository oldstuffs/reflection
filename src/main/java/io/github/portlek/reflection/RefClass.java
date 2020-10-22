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
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine {@link Class}.
 *
 * @param <T> the class's type.
 */
public interface RefClass<T> extends RefAnnotated {

  /**
   * gets a field list that has the given annotation.
   *
   * @param annotationClass the annotation class to get.
   * @param <A> the annotation type.
   *
   * @return a field list.
   */
  @NotNull
  default <A extends Annotation> List<RefField> getFieldsWithAnnotation(@NotNull final Class<A> annotationClass) {
    return this.getFields().stream()
      .filter(refField -> refField.hasAnnotation(annotationClass))
      .collect(Collectors.toList());
  }

  /**
   * gets a field list that has the given annotation and runs the consumer for each found field.
   *
   * @param annotationClass the annotation class to get.
   * @param consumer the consumer to run.
   * @param <A> the annotation type.
   */
  default <A extends Annotation> void getFieldsWithAnnotation(@NotNull final Class<A> annotationClass,
                                                              @NotNull final BiConsumer<RefField, A> consumer) {
    this.getFields().forEach(refField ->
      refField.getAnnotation(annotationClass, a -> consumer.accept(refField, a)));
  }

  /**
   * gets a declared field list that has the given annotation.
   *
   * @param annotationClass the annotation class to get.
   * @param <A> the annotation type.
   *
   * @return a field list.
   */
  @NotNull
  default <A extends Annotation> List<RefField> getDeclaredFieldsWithAnnotation(
    @NotNull final Class<A> annotationClass) {
    return this.getDeclaredFields().stream()
      .filter(refField -> refField.hasAnnotation(annotationClass))
      .collect(Collectors.toList());
  }

  /**
   * gets a declared field list that has the given annotation and runs the consumer for each found field.
   *
   * @param annotationClass the annotation class to get.
   * @param consumer the consumer to run.
   * @param <A> the annotation type.
   */
  default <A extends Annotation> void getDeclaredFieldsWithAnnotation(@NotNull final Class<A> annotationClass,
                                                                      @NotNull final BiConsumer<RefField, A> consumer) {
    this.getDeclaredFields().forEach(refField ->
      refField.getAnnotation(annotationClass, a -> consumer.accept(refField, a)));
  }

  /**
   * gets a method list that has the given annotation.
   *
   * @param annotationClass the annotation class to get.
   * @param <A> the annotation type.
   *
   * @return a method list.
   */
  @NotNull
  default <A extends Annotation> List<RefMethod> getMethodsWithAnnotation(@NotNull final Class<A> annotationClass) {
    return this.getMethods().stream()
      .filter(refField -> refField.hasAnnotation(annotationClass))
      .collect(Collectors.toList());
  }

  /**
   * gets a method list that has the given annotation and runs the consumer for each found method.
   *
   * @param annotationClass the annotation class to get.
   * @param consumer the consumer to run.
   * @param <A> the annotation type.
   */
  default <A extends Annotation> void getMethodsWithAnnotation(@NotNull final Class<A> annotationClass,
                                                               @NotNull final BiConsumer<RefMethod, A> consumer) {
    this.getMethods().forEach(refField ->
      refField.getAnnotation(annotationClass, a -> consumer.accept(refField, a)));
  }

  /**
   * gets a method field list that has the given annotation.
   *
   * @param annotationClass the annotation class to get.
   * @param <A> the annotation type.
   *
   * @return a method list.
   */
  @NotNull
  default <A extends Annotation> List<RefMethod> getDeclaredMethodsWithAnnotation(
    @NotNull final Class<A> annotationClass) {
    return this.getDeclaredMethods().stream()
      .filter(refMethod -> refMethod.hasAnnotation(annotationClass))
      .collect(Collectors.toList());
  }

  /**
   * gets a declared method list that has the given annotation and runs the consumer for each found method.
   *
   * @param annotationClass the annotation class to get.
   * @param consumer the consumer to run.
   * @param <A> the annotation type.
   */
  default <A extends Annotation> void getDeclaredMethodsWithAnnotation(@NotNull final Class<A> annotationClass,
                                                                       @NotNull final BiConsumer<RefMethod, A> consumer) {
    this.getDeclaredMethods().forEach(refField ->
      refField.getAnnotation(annotationClass, a -> consumer.accept(refField, a)));
  }

  /**
   * obtains the real class.
   *
   * @return the real class.
   */
  @NotNull
  Class<T> getRealClass();

  /**
   * checks if the given object is instance of {@code this}.
   *
   * @param object the object to check.
   *
   * @return true if object is an instance of this class.
   *
   * @see Class#isInstance(Object)
   */
  boolean isInstance(@NotNull Object object);

  /**
   * gets existing method by name and types.
   *
   * @param name the name to get.
   * @param types the method parameter types to get.
   *
   * @return a {@link RefMethod} object.
   */
  @NotNull
  Optional<RefMethod> getPrimitiveMethod(@NotNull String name, @NotNull Object... types);

  /**
   * gets existing method by name and types.
   *
   * @param name the name to get.
   * @param types method parameter types to get.
   *
   * @return a {@link RefMethod} object.
   */
  @NotNull
  Optional<RefMethod> getMethod(@NotNull String name, @NotNull Object... types);

  /**
   * gets method by type parameters.
   *
   * @param types the parameter types to get.
   *
   * @return a {@link RefMethod} object.
   */
  @NotNull
  Optional<RefMethod> getPrimitiveMethodByParameter(@NotNull Object... types);

  /**
   * gets method by type parameters.
   *
   * @param types the parameter types to get.
   *
   * @return a {@link RefMethod} object.
   */
  @NotNull
  Optional<RefMethod> getMethodByParameter(@NotNull Object... types);

  /**
   * gets method by name.
   *
   * @param names the names to get.
   *
   * @return a {@link RefMethod} object.
   */
  @NotNull
  Optional<RefMethod> getMethodByName(@NotNull String... names);

  /**
   * gets method by return value.
   *
   * @param type the type to get.
   * @param <X> the class type of the return type.
   *
   * @return a {@link RefMethod} object.
   */
  @NotNull <X> Optional<RefMethod> getMethodByReturnType(@NotNull RefClass<X> type);

  /**
   * gets method by return value.
   *
   * @param type the type to get.
   *
   * @return a {@link RefMethod} object.
   */
  @NotNull
  Optional<RefMethod> getMethodByReturnType(@NotNull Class<?> type);

  /**
   * gets existing constructor by types.
   *
   * @param types the parameter types to get.
   *
   * @return a {@link RefConstructed} object.
   */
  @NotNull
  Optional<RefConstructed<T>> getPrimitiveConstructor(@NotNull Object... types);

  /**
   * gets existing constructor by types.
   *
   * @param types the parameter types to get.
   *
   * @return a {@link RefConstructed} object.
   */
  @NotNull
  Optional<RefConstructed<T>> getConstructor(@NotNull Object... types);

  /**
   * gets constructor by number of arguments.
   *
   * @param number the number to get.
   *
   * @return a {@link RefConstructed} object.
   */
  @NotNull
  Optional<RefConstructed<T>> getConstructor(int number);

  /**
   * gets field by name.
   *
   * @param name the name to get.
   *
   * @return a {@link RefField} object.
   */
  @NotNull
  Optional<RefField> getField(@NotNull String name);

  /**
   * gets field by type.
   *
   * @param type the type to get
   * @param <X> the class type of the field.
   *
   * @return a {@link RefField} object.
   */
  @NotNull <X> Optional<RefField> getField(@NotNull RefClass<X> type);

  /**
   * gets field by type.
   *
   * @param type the type to get.
   *
   * @return a {@link RefField} object.
   */
  @NotNull
  Optional<RefField> getField(@NotNull Class<?> type);

  /**
   * gets all fields.
   *
   * @return all fields of the class.
   */
  @NotNull
  List<RefField> getFields();

  /**
   * gets all declared fields.
   *
   * @return all declared fields of the class.
   */
  @NotNull
  List<RefField> getDeclaredFields();

  /**
   * gets all methods.
   *
   * @return all methods of the class.
   */
  @NotNull
  List<RefMethod> getMethods();

  /**
   * gets all declared methods.
   *
   * @return all declared methods of the class.
   */
  @NotNull
  List<RefMethod> getDeclaredMethods();
}
