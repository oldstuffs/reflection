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

package io.github.portlek.reflection.field;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.clazz.ClassOf;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

final class FieldOfTest {

    private static final Object FIELD_TEST = new FieldOfTest.FieldTest();

    private final RefClass<FieldOfTest.FieldTest> refClass = new ClassOf<>(FieldOfTest.FieldTest.class);

    @Test
    void get() throws Throwable {
        new Assertion<>(
            "Couldn't get the field",
            this.refClass.field("text")
                .orElseThrow(() ->
                    new NoSuchFieldException("Cannot find field!"))
                .of(FieldOfTest.FIELD_TEST)
                .get()
                .orElseThrow(() ->
                    new NoSuchFieldException("Cannot find field!")),
            new IsEqual<>("Test Text")
        ).affirm();

        new Assertion<>(
            "Couldn't get the field",
            this.refClass.field("TEXT")
                .orElseThrow(() ->
                    new NoSuchFieldException("Cannot find field!"))
                .of(FieldOfTest.FIELD_TEST)
                .get()
                .orElseThrow(() ->
                    new NoSuchFieldException("Cannot find field!")),
            new IsEqual<>("Static Test Text")
        ).affirm();
    }

    @Test
    void set() throws NoSuchFieldException {
        this.refClass.field("text")
            .orElseThrow(() ->
                new NoSuchFieldException("Cannot find field!"))
            .of(FieldOfTest.FIELD_TEST)
            .set("Edited Test Text");

        new Assertion<>(

            "Couldn't set the field!",
            this.refClass.field("text")
                .orElseThrow(() ->
                    new NoSuchFieldException("Cannot find field!"))
                .of(FieldOfTest.FIELD_TEST)
                .get()
                .orElseThrow(() ->
                    new NoSuchFieldException("Cannot find field!")),
            new IsEqual<>("Edited Test Text")
        ).affirm();
    }

    private static final class FieldTest {

        private static final String TEXT = "Static Test Text";

        private final String text = "Test Text";

    }

}