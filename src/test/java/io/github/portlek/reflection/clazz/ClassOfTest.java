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

package io.github.portlek.reflection.clazz;

import io.github.portlek.reflection.RefClass;
import org.hamcrest.core.IsEqual;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

final class ClassOfTest {

  private static final RefClass<ClassOfTest.TestClass> CLASS = new ClassOf<>(ClassOfTest.TestClass.class);

  @Test
  void getRealClass() {
    new Assertion<>(
      "Class is not equal to real class!",
      ClassOfTest.CLASS.getRealClass(),
      new IsEqual<>(ClassOfTest.TestClass.class)
    ).affirm();
  }

  @Test
  void isInstance() {
    new Assertion<>(
      "Class is not instance of the object",
      ClassOfTest.CLASS.isInstance(new ClassOfTest.TestTestClass("Hasan", 21)),
      new IsTrue()
    ).affirm();
  }

  @Test
  void getMethod() {
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethod("voidMethod").isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethod("returnStringMethod").isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethod("voidParameterMethod", String.class, Integer.class).isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethod("returnIntegerParameterMethod", String.class, Integer.class).isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getPrimitiveMethod("voidPrimitiveParameterMethod", String.class, Integer.class)
        .isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getPrimitiveMethod("returnIntPrimitiveParameterMethod", String.class,
        Integer.class).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void findMethodByParameter() {
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByParameter().isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByParameter(String.class, Integer.class).isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByParameter(String.class, int.class).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void findPrimitiveMethodByParameter() {
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getPrimitiveMethodByParameter().isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getPrimitiveMethodByParameter(String.class, Integer.class).isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getPrimitiveMethodByParameter(String.class, int.class).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void findMethodByName() {
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByName("voidMethod").isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByName("returnStringMethod").isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByName("voidParameterMethod").isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByName("returnIntegerParameterMethod").isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByName("voidPrimitiveParameterMethod").isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByName("returnIntPrimitiveParameterMethod").isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void findMethodByReturnType() {
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByReturnType(void.class).isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByReturnType(String.class).isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByReturnType(Integer.class).isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByReturnType(int.class).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void findMethodByReturnTypeAsRefClass() {
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByReturnType(new ClassOf<>(void.class)).isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByReturnType(new ClassOf<>(String.class)).isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByReturnType(new ClassOf<>(Integer.class)).isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find method",
      ClassOfTest.CLASS.getMethodByReturnType(new ClassOf<>(int.class)).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void getPrimitiveConstructor() {
    new Assertion<>(
      "Cannot find constructor!",
      ClassOfTest.CLASS.getPrimitiveConstructor(String.class, Integer.class).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void findConstructor() {
    new Assertion<>(
      "Cannot find constructor!",
      ClassOfTest.CLASS.getConstructor(2).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void getConstructor() {
    new Assertion<>(
      "Cannot find constructor!",
      ClassOfTest.CLASS.getConstructor().isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find constructor!",
      ClassOfTest.CLASS.getConstructor(String.class, int.class).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void getField() {
    new Assertion<>(
      "Cannot find field!",
      ClassOfTest.CLASS.getField("text").isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void findFieldFromClass() {
    new Assertion<>(
      "Cannot find field!",
      ClassOfTest.CLASS.getField(String.class).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void findFieldFromRefClass() {
    new Assertion<>(
      "Cannot find field!",
      ClassOfTest.CLASS.getField(new ClassOf<>(String.class)).isPresent(),
      new IsTrue()
    ).affirm();
  }

  private static class TestTestClass extends ClassOfTest.TestClass {

    TestTestClass(final String text, final int age) {
      super(text, age);
    }
  }

  private static class TestClass {

    @NotNull
    private final String text;

    private final int age;

    TestClass(@NotNull final String text, final int age) {
      this.text = text;
      this.age = age;
    }

    TestClass() {
      this("Hasan", 21);
    }

    private void voidMethod() {
    }

    private String returnStringMethod() {
      return "Called Return Method!";
    }

    private void voidParameterMethod(final String param1, final Integer param2) {
    }

    private Integer returnIntegerParameterMethod(final String param1, final Integer param2) {
      return 1;
    }

    private void voidPrimitiveParameterMethod(final String param1, final int param2) {
    }

    private int returnIntPrimitiveParameterMethod(final String param1, final int param2) {
      return 1;
    }
  }
}
