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
            ClassOfTest.CLASS.realClass(),
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
            ClassOfTest.CLASS.method("voidMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.method("returnStringMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.method("voidParameterMethod", String.class, Integer.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.method("returnIntegerParameterMethod", String.class, Integer.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.primitiveMethod("voidPrimitiveParameterMethod", String.class, Integer.class)
                .isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.primitiveMethod("returnIntPrimitiveParameterMethod", String.class,
                Integer.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findMethodByParameter() {
        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByParameter().isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByParameter(String.class, Integer.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByParameter(String.class, int.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findPrimitiveMethodByParameter() {
        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.primitiveMethodByParameter().isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.primitiveMethodByParameter(String.class, Integer.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.primitiveMethodByParameter(String.class, int.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findMethodByName() {
        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByName("voidMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByName("returnStringMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByName("voidParameterMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByName("returnIntegerParameterMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByName("voidPrimitiveParameterMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByName("returnIntPrimitiveParameterMethod").isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findMethodByReturnType() {
        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByReturnType(void.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByReturnType(String.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByReturnType(Integer.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByReturnType(int.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findMethodByReturnTypeAsRefClass() {
        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByReturnType(new ClassOf<>(void.class)).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByReturnType(new ClassOf<>(String.class)).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByReturnType(new ClassOf<>(Integer.class)).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.methodByReturnType(new ClassOf<>(int.class)).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void getPrimitiveConstructor() {
        new Assertion<>(
            "Cannot find constructor!",
            ClassOfTest.CLASS.primitiveConstructor(String.class, Integer.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findConstructor() {
        new Assertion<>(
            "Cannot find constructor!",
            ClassOfTest.CLASS.constructor(2).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void getConstructor() {
        new Assertion<>(
            "Cannot find constructor!",
            ClassOfTest.CLASS.constructor().isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find constructor!",
            ClassOfTest.CLASS.constructor(String.class, int.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void getField() {
        new Assertion<>(
            "Cannot find field!",
            ClassOfTest.CLASS.field("text").isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findFieldFromClass() {
        new Assertion<>(
            "Cannot find field!",
            ClassOfTest.CLASS.field(String.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findFieldFromRefClass() {
        new Assertion<>(
            "Cannot find field!",
            ClassOfTest.CLASS.field(new ClassOf<>(String.class)).isPresent(),
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