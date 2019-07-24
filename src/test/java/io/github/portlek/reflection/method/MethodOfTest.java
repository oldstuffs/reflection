package io.github.portlek.reflection.method;

import io.github.portlek.reflection.LoggerOf;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class MethodOfTest {

    private static final Logger LOGGER = new LoggerOf(
        MethodOfTest.class,
        MethodTest.class
    );

    @Test
    void of() {

    }

    @Test
    void call() {

    }

    private static final class MethodTest {

        private void callVoidMethod() {
            LOGGER.info("callVoidMethod -> Test Passed!");
        }

        private String callReturnMethod() {
            return "Called Return Method!";
        }

    }

}