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
import io.github.portlek.reflection.clazz.ClassOf;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

final class ConstructorOfTest {

  @NotNull
  private static final RefConstructed<ConstructorTest> CONSTRUCTOR;

  static {
    try {
      CONSTRUCTOR = new ClassOf<>(ConstructorTest.class)
        .getPrimitiveConstructor(String.class, int.class)
        .orElseThrow(() ->
          new NoSuchMethodException("Cannot find constructor!"));
    } catch (final NoSuchMethodException exception) {
      throw new RuntimeException(exception);
    }
  }

  @Test
  void create() throws NoSuchMethodException {
    new Assertion<>(
      "Cannot created object of the Constructed!",
      ConstructorOfTest.CONSTRUCTOR
        .create("Hasan", 21)
        .orElseThrow(() ->
          new NoSuchMethodException("Couldn't create object from constructor!")),
      new IsInstanceOf(ConstructorOfTest.ConstructorTest.class)
    ).affirm();
  }

  @Test
  void hasFinal() {
    new Assertion<>(
      "Cannot get the class's modifier correctly",
      ConstructorOfTest.CONSTRUCTOR.hasFinal(),
      new IsNot<>(new IsTrue())
    ).affirm();
  }

  @Test
  void hasPrivate() {
    new Assertion<>(
      "Cannot get the class's modifier correctly",
      ConstructorOfTest.CONSTRUCTOR.hasPrivate(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void hasPublic() {
    new Assertion<>(
      "Cannot get the class's modifier correctly",
      ConstructorOfTest.CONSTRUCTOR.hasPublic(),
      new IsNot<>(new IsTrue())
    ).affirm();
  }

  @Test
  void hasStatic() {
    new Assertion<>(
      "Cannot get the class's modifier correctly",
      ConstructorOfTest.CONSTRUCTOR.hasStatic(),
      new IsNot<>(new IsTrue())
    ).affirm();
  }

  private static class ConstructorTest {

    private final int age;

    @NotNull
    private final String name;

    private ConstructorTest(@NotNull final String text, final int age) {
      this.name = text;
      this.age = age;
    }
  }
}
