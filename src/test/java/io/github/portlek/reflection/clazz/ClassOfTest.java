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

package io.github.portlek.reflection.clazz;

import io.github.portlek.reflection.Anno;
import io.github.portlek.reflection.RefClass;
import java.util.concurrent.atomic.AtomicReference;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

final class ClassOfTest {

  private static final RefClass<ClassOfTest.TestClass> CLASS = new ClassOf<>(ClassOfTest.TestClass.class);

  @Test
  void findConstructor() {
    new Assertion<>(
      "Cannot find constructor!",
      ClassOfTest.CLASS.getConstructor(2).isPresent(),
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
  void getAnnotation() {
    new Assertion<>(
      "Cannot find the annotation!",
      ClassOfTest.CLASS.getAnnotation(Anno.class).isPresent(),
      new IsTrue()
    ).affirm();
    final var count = new AtomicReference<String>();
    ClassOfTest.CLASS.getAnnotation(Anno.class, anno -> count.set(anno.value()));
    new Assertion<>(
      "Cannot run the consumer!",
      count.get(),
      new IsEqual<>("test")
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
    new Assertion<>(
      "Cannot find constructor!",
      ClassOfTest.CLASS.getConstructor(2).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void getDeclaredFields() {
    new Assertion<>(
      "Cannot find declared fields",
      ClassOfTest.CLASS.getDeclaredFields().size(),
      new IsEqual<>(2)
    ).affirm();
  }

  @Test
  void getDeclaredMethods() {
    new Assertion<>(
      "Cannot find declared methods",
      ClassOfTest.CLASS.getDeclaredMethods().size(),
      new IsEqual<>(6)
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
  void getFields() {
    new Assertion<>(
      "Cannot find declared fields",
      ClassOfTest.CLASS.getFields().size(),
      new IsEqual<>(0)
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
  void getMethodByName() {
    new Assertion<>(
      "Cannot find the method by name",
      ClassOfTest.CLASS.getMethodByName("voidMethod").isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void getMethodByParameter() {
    new Assertion<>(
      "Cannot find the method by parameter",
      ClassOfTest.CLASS.getMethodByParameter(String.class, Integer.class).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void getMethodByReturnType() {
    new Assertion<>(
      "Cannot find the method by return type",
      ClassOfTest.CLASS.getMethodByReturnType(String.class).isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find the method by return type",
      ClassOfTest.CLASS.getMethodByReturnType(Integer.class).isPresent(),
      new IsTrue()
    ).affirm();
    new Assertion<>(
      "Cannot find the method by return type",
      ClassOfTest.CLASS.getMethodByReturnType(void.class).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void getMethods() {
    new Assertion<>(
      "Cannot find methods",
      ClassOfTest.CLASS.getMethods().size(),
      new IsEqual<>(9)
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
  void getPrimitiveMethod() {
    new Assertion<>(
      "Cannot find primitive method",
      ClassOfTest.CLASS.getPrimitiveMethod("returnIntPrimitiveParameterMethod", String.class, int.class).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void getPrimitiveMethodByParameter() {
    new Assertion<>(
      "Cannot find primitive method",
      ClassOfTest.CLASS.getPrimitiveMethodByParameter(String.class, int.class).isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void getRealClass() {
    new Assertion<>(
      "Class is not equal to real class!",
      ClassOfTest.CLASS.getRealClass(),
      new IsEqual<>(ClassOfTest.TestClass.class)
    ).affirm();
  }

  @Test
  void hasFinal() {
    new Assertion<>(
      "Cannot get the class's modifier correctly",
      ClassOfTest.CLASS.hasFinal(),
      new IsNot<>(new IsTrue())
    ).affirm();
  }

  @Test
  void hasPrivate() {
    new Assertion<>(
      "Cannot get the class's modifier correctly",
      ClassOfTest.CLASS.hasPrivate(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void hasPublic() {
    new Assertion<>(
      "Cannot get the class's modifier correctly",
      ClassOfTest.CLASS.hasPublic(),
      new IsNot<>(new IsTrue())
    ).affirm();
  }

  @Test
  void hasStatic() {
    new Assertion<>(
      "Cannot get the class's modifier correctly",
      ClassOfTest.CLASS.hasStatic(),
      new IsTrue()
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

  @Anno("test")
  private static class TestClass {

    private final int age;

    private final String text;

    TestClass(final String text, final int age) {
      this.text = text;
      this.age = age;
    }

    TestClass() {
      this("Hasan", 21);
    }

    private int returnIntPrimitiveParameterMethod(final String param1, final int param2) {
      return 1;
    }

    private Integer returnIntegerParameterMethod(final String param1, final Integer param2) {
      return 1;
    }

    private String returnStringMethod() {
      return "Called Return Method!";
    }

    private void voidMethod() {
    }

    private void voidParameterMethod(final String param1, final Integer param2) {
    }

    private void voidPrimitiveParameterMethod(final String param1, final int param2) {
    }
  }

  private static class TestTestClass extends ClassOfTest.TestClass {

    TestTestClass(final String text, final int age) {
      super(text, age);
    }
  }
}
