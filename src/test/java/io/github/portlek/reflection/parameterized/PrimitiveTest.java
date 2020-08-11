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

package io.github.portlek.reflection.parameterized;

import java.util.function.Supplier;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

final class PrimitiveTest {

    private static final Supplier<Class<Integer>> INTEGER = new Primitive<>(int.class);

    private static final Supplier<Class<Float>> FLOAT = new Primitive<>(float.class);

    private static final Supplier<Class<Short>> SHORT = new Primitive<>(short.class);

    private static final Supplier<Class<Character>> CHARACTER = new Primitive<>(char.class);

    private static final Supplier<Class<Boolean>> BOOLEAN = new Primitive<>(boolean.class);

    private static final Supplier<Class<Byte>> BYTE = new Primitive<>(byte.class);

    private static final Supplier<Class<Long>> LONG = new Primitive<>(long.class);

    private static final Supplier<Class<Void>> VOID = new Primitive<>(void.class);

    private static final Supplier<Class<Double>> DOUBLE = new Primitive<>(double.class);

    @Test
    void get() {
        new Assertion<>(
            "Couldn't convert into Integer",
            PrimitiveTest.INTEGER.get(),
            new IsEqual<>(Integer.TYPE)
        ).affirm();
        new Assertion<>(
            "Couldn't convert into Float",
            PrimitiveTest.FLOAT.get(),
            new IsEqual<>(Float.TYPE)
        ).affirm();
        new Assertion<>(
            "Couldn't convert into Short",
            PrimitiveTest.SHORT.get(),
            new IsEqual<>(Short.TYPE)
        ).affirm();
        new Assertion<>(
            "Couldn't convert into Character",
            PrimitiveTest.CHARACTER.get(),
            new IsEqual<>(Character.TYPE)
        ).affirm();
        new Assertion<>(
            "Couldn't convert into Boolean",
            PrimitiveTest.BOOLEAN.get(),
            new IsEqual<>(Boolean.TYPE)
        ).affirm();
        new Assertion<>(
            "Couldn't convert into Byte",
            PrimitiveTest.BYTE.get(),
            new IsEqual<>(Byte.TYPE)
        ).affirm();
        new Assertion<>(
            "Couldn't convert into Long",
            PrimitiveTest.LONG.get(),
            new IsEqual<>(Long.TYPE)
        ).affirm();
        new Assertion<>(
            "Couldn't convert into Void",
            PrimitiveTest.VOID.get(),
            new IsEqual<>(Void.TYPE)
        ).affirm();
        new Assertion<>(
            "Couldn't convert into Double",
            PrimitiveTest.DOUBLE.get(),
            new IsEqual<>(Double.TYPE)
        ).affirm();
    }

}