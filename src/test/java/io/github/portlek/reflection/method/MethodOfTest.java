package io.github.portlek.reflection.method;

import io.github.portlek.reflection.LoggerOf;
import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.clazz.ClassOf;
import io.github.portlek.reflection.mck.MckMethod;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

import java.util.logging.Logger;

class MethodOfTest {

    private static final Logger LOGGER = new LoggerOf(
        MethodOfTest.class,
        MethodTest.class
    );

    private final MethodTest METHOD_TEST = new MethodTest();
    private final RefClass CLASS = new ClassOf(METHOD_TEST);

    @Test
    void of() {
        new Assertion<>(
            "Couldn't applied method!",
            CLASS.getMethod("callVoidMethod").of(METHOD_TEST),
            new IsNot<>(
                new IsInstanceOf(MckMethod.MckMethodExecuted.class)
            )
        ).affirm();

        new Assertion<>(
            "Couldn't applied method!",
            CLASS.getMethod("callReturnParameterMethod", 21).of(METHOD_TEST),
            new IsNot<>(
                new IsInstanceOf(MckMethod.MckMethodExecuted.class)
            )
        ).affirm();

        new Assertion<>(
            "Couldn't applied method!",
            CLASS.getPrimitiveMethod("callPrimitiveReturnParameterMethod", 21).of(METHOD_TEST),
            new IsNot<>(
                new IsInstanceOf(MckMethod.MckMethodExecuted.class)
            )
        ).affirm();
    }

    @Test
    void call() {
        new Assertion<>(
            "Couldn't call method",
            CLASS.getMethod("callVoidMethod").of(METHOD_TEST).call(),
            new IsNull<>()
        ).affirm();

        new Assertion<>(
            "Couldn't call method",
            CLASS.getMethod("callReturnMethod").of(METHOD_TEST).call(),
            new IsEqual<>("Called Return Method!")
        ).affirm();

        new Assertion<>(
            "Couldn't call method",
            CLASS.getMethod("callVoidParameterMethod", String.class).of(METHOD_TEST).call("Hasan"),
            new IsNull<>()
        ).affirm();

        new Assertion<>(
            "Couldn't call method",
            CLASS.getMethod("callReturnParameterMethod", 21).of(METHOD_TEST).call(21),
            new IsEqual<>("Called Return Method with 21")
        ).affirm();

        new Assertion<>(
            "Couldn't call method",
            CLASS.getPrimitiveMethod("callPrimitiveReturnParameterMethod", 21).of(METHOD_TEST).call(21),
            new IsEqual<>("Called Return Method with 21")
        ).affirm();
    }

    private static final class MethodTest {

        private void callVoidMethod() {
            LOGGER.info("callVoidMethod -> Test Passed!");
        }

        private String callReturnMethod() {
            return "Called Return Method!";
        }

        private void callVoidParameterMethod(String text) {
            LOGGER.info("callVoidParameterMethod -> Test Passed with " + text);
        }

        private String callReturnParameterMethod(Integer age) {
            return "Called Return Method with " + age;
        }

        private String callPrimitiveReturnParameterMethod(int age) {
            return "Called Return Method with " + age;
        }

    }

}