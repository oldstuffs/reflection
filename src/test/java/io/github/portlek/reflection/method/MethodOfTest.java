package io.github.portlek.reflection.method;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.RefMethodExecuted;
import io.github.portlek.reflection.clazz.ClassOf;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.Throws;

final class MethodOfTest {

    private final MethodOfTest.MethodTest METHOD_TEST = new MethodOfTest.MethodTest();

    private final RefClass<MethodOfTest.MethodTest> CLASS = new ClassOf<>(this.METHOD_TEST);

    @Test
    void of() throws Throwable {
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

    private static final class MethodTest {

        private void callVoidMethod() {

        }

        private String callReturnMethod() {
            return "Called Return Method!";
        }

        private void callVoidParameterMethod(final String text) {

        }

        private String callReturnParameterMethod(final Integer age) {
            return "Called Return Method with " + age;
        }

        private String callPrimitiveReturnParameterMethod(final int age) {
            return "Called Return Method with " + age;
        }

    }

}