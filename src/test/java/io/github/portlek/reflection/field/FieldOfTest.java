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

import io.github.portlek.reflection.Anno;
import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.clazz.ClassOf;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

final class FieldOfTest {

  private static final Object FIELD_TEST = new FieldOfTest.FieldTest();

  private final RefClass<FieldOfTest.FieldTest> refClass = new ClassOf<>(FieldOfTest.FieldTest.class);

  @Test
  void get() throws NoSuchFieldException {
    new Assertion<>(
      "Couldn't get the field",
      this.refClass.getField("text")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!"))
        .of(FieldOfTest.FIELD_TEST)
        .getValue()
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!")),
      new IsEqual<>("Test Text")
    ).affirm();
    new Assertion<>(
      "Couldn't get the field",
      this.refClass.getField("TEXT")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!"))
        .of(FieldOfTest.FIELD_TEST)
        .getValue()
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!")),
      new IsEqual<>("Static Test Text")
    ).affirm();
  }

  @Test
  void getAnnotation() throws NoSuchFieldException {
    new Assertion<>(
      "Cannot find annotation on the field",
      this.refClass.getField("TEXT")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!"))
        .getAnnotation(Anno.class)
        .isPresent(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void getName() throws NoSuchFieldException {
    new Assertion<>(
      "Cannot find the name of the field",
      this.refClass.getField("TEXT")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!"))
        .getName(),
      new IsEqual<>("TEXT")
    ).affirm();
  }

  @Test
  void getRealField() throws NoSuchFieldException {
    new Assertion<>(
      "Cannot find the real field instance of the field",
      this.refClass.getField("TEXT")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!"))
        .getRealField(),
      new IsEqual<>(FieldTest.class.getDeclaredField("TEXT"))
    ).affirm();
  }

  @Test
  void getType() throws NoSuchFieldException {
    new Assertion<>(
      "Cannot find type of the field",
      this.refClass.getField("TEXT")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!"))
        .getType(),
      new IsEqual<>(String.class)
    ).affirm();
  }

  @Test
  void hasFinal() throws NoSuchFieldException {
    new Assertion<>(
      "Cannot find final modifier of the field",
      this.refClass.getField("TEXT")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!"))
        .hasFinal(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void hasPrivate() throws NoSuchFieldException {
    new Assertion<>(
      "Cannot find private modifier of the field",
      this.refClass.getField("TEXT")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!"))
        .hasPrivate(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void hasPublic() throws NoSuchFieldException {
    new Assertion<>(
      "Found public modifier of the field",
      this.refClass.getField("TEXT")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!"))
        .hasPublic(),
      new IsNot<>(new IsTrue())
    ).affirm();
  }

  @Test
  void hasStatic() throws NoSuchFieldException {
    new Assertion<>(
      "Cannot find static modifier of the field",
      this.refClass.getField("TEXT")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!"))
        .hasStatic(),
      new IsTrue()
    ).affirm();
  }

  @Test
  void set() throws NoSuchFieldException {
    this.refClass.getField("text")
      .orElseThrow(() ->
        new NoSuchFieldException("Cannot find field!"))
      .of(FieldOfTest.FIELD_TEST)
      .setValue("Edited Test Text");
    new Assertion<>(
      "Couldn't set the field!",
      this.refClass.getField("text")
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!"))
        .of(FieldOfTest.FIELD_TEST)
        .getValue()
        .orElseThrow(() ->
          new NoSuchFieldException("Cannot find field!")),
      new IsEqual<>("Edited Test Text")
    ).affirm();
  }

  private static final class FieldTest {

    @Anno("TEXT")
    private static final String TEXT = "Static Test Text";

    @Anno("text")
    private final String text = "Test Text";
  }
}
