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

import io.github.portlek.reflection.Anno;
import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.RefMethodExecuted;
import io.github.portlek.reflection.clazz.ClassOf;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;
import org.llorllale.cactoos.matchers.Throws;

final class MethodOfTest {

  private final MethodOfTest.MethodTest METHOD_TEST = new MethodOfTest.MethodTest();

  private final RefClass<MethodOfTest.MethodTest> CLASS = new ClassOf<>(this.METHOD_TEST);

  @Test
  void call() throws NoSuchFieldException {
    new Assertion<>(
      "Couldn't call method",
      () -> this.CLASS.getMethod("callVoidMethod")
        .orElseThrow(() ->
          new NoSuchMethodException("Cannot find method!"))
        .of(this.METHOD_TEST)
        .call()
        .orElseThrow(() ->
          new NoSuchMethodException("Couldn't call method!")),
      new IsNot<>(new Throws<>(RuntimeException.class))
    ).affirm();
    new Assertion<>(
      "Couldn't call method",
      this.CLASS.getMethod("callReturnMethod")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find method!"))
        .of(this.METHOD_TEST)
        .call()
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find method!")),
      new IsEqual<>("Called Return Method!")
    ).affirm();
    new Assertion<>(
      "Couldn't call method",
      () -> this.CLASS.getMethod("callVoidParameterMethod", String.class)
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find method!"))
        .of(this.METHOD_TEST)
        .call("Hasan")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find method!")),
      new IsNot<>(new Throws<>(RuntimeException.class))
    ).affirm();
    new Assertion<>(
      "Couldn't call method",
      this.CLASS.getMethod("callReturnParameterMethod", 21)
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find method!"))
        .of(this.METHOD_TEST)
        .call(21)
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find method!")),
      new IsEqual<>("Called Return Method with 21")
    ).affirm();
    new Assertion<>(
      "Couldn't call method",
      this.CLASS.getPrimitiveMethod("callPrimitiveReturnParameterMethod", 21)
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find method!"))
        .of(this.METHOD_TEST)
        .call(21)
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find method!")),
      new IsEqual<>("Called Return Method with 21")
    ).affirm();
  }

  @Test
  void getAnnotation() throws NoSuchMethodException {
    new Assertion<>(
      "Cannot find the annotation",
      this.CLASS.getMethod("callVoidMethod")
        .orElseThrow(() ->
          new NoSuchMethodException("Cannot find method!"))
        .getAnnotation(Anno.class)
        .isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void getName() throws NoSuchMethodException {
    new Assertion<>(
      "Cannot find the name of the method",
      this.CLASS.getMethod("callVoidMethod")
        .orElseThrow(() ->
          new NoSuchMethodException("Cannot find method!"))
        .getName(),
      new IsEqual<>("callVoidMethod")
    ).affirm();
  }

  @Test
  void getParameterTypes() throws NoSuchMethodException {
    new Assertion<>(
      "Cannot find parameter types of the method",
      this.CLASS.getMethod("callVoidMethod")
        .orElseThrow(() ->
          new NoSuchMethodException("Cannot find method!"))
        .getParameterTypes()
        .length,
      new IsEqual<>(0)
    ).affirm();
  }

  @Test
  void getReturnType() throws NoSuchMethodException {
    new Assertion<>(
      "Cannot find return type of the method",
      this.CLASS.getMethod("callVoidMethod")
        .orElseThrow(() ->
          new NoSuchMethodException("Cannot find method!"))
        .getReturnType(),
      new IsEqual<>(void.class)
    ).affirm();
  }

  @Test
  void hasFinal() throws NoSuchMethodException {
    new Assertion<>(
      "Found final modifier of the method",
      this.CLASS.getMethod("callVoidMethod")
        .orElseThrow(() ->
          new NoSuchMethodException("Cannot find method!"))
        .hasStatic(),
      new IsNot<>(new IsTrue())
    ).affirm();
  }

  @Test
  void hasPrivate() throws NoSuchMethodException {
    new Assertion<>(
      "Cannot find private modifier of the method",
      this.CLASS.getMethod("callVoidMethod")
        .orElseThrow(() ->
          new NoSuchMethodException("Cannot find method!"))
        .hasStatic(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void hasPublic() throws NoSuchMethodException {
    new Assertion<>(
      "Found public modifier of the method",
      this.CLASS.getMethod("callVoidMethod")
        .orElseThrow(() ->
          new NoSuchMethodException("Cannot find method!"))
        .hasPublic(),
      new IsNot<>(new IsTrue())
    ).affirm();
  }

  @Test
  void hasStatic() throws NoSuchMethodException {
    new Assertion<>(
      "Found static modifier of the method",
      this.CLASS.getMethod("callVoidMethod")
        .orElseThrow(() ->
          new NoSuchMethodException("Cannot find method!"))
        .hasStatic(),
      new IsNot<>(new IsTrue())
    ).affirm();
  }

  @Test
  void of() throws NoSuchFieldException {
    new Assertion<>(
      "Couldn't applied method!",
      this.CLASS.getMethod("callVoidMethod")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find method!"))
        .of(this.METHOD_TEST),
      new IsInstanceOf(RefMethodExecuted.class)
    ).affirm();
    new Assertion<>(
      "Couldn't applied method!",
      this.CLASS.getMethod("callReturnParameterMethod", 21)
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find method!"))
        .of(this.METHOD_TEST),
      new IsInstanceOf(RefMethodExecuted.class)
    ).affirm();
    new Assertion<>(
      "Couldn't applied method!",
      this.CLASS.getPrimitiveMethod("callPrimitiveReturnParameterMethod", 21)
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find method!"))
        .of(this.METHOD_TEST),
      new IsInstanceOf(RefMethodExecuted.class)
    ).affirm();
  }

  private static final class MethodTest {

    private String callPrimitiveReturnParameterMethod(final int age) {
      return "Called Return Method with " + age;
    }

    private String callReturnMethod() {
      return "Called Return Method!";
    }

    private String callReturnParameterMethod(final Integer age) {
      return "Called Return Method with " + age;
    }

    @Anno("callVoidMethod")
    private void callVoidMethod() {
    }

    private void callVoidParameterMethod(final String text) {
    }
  }
}
