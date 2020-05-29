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