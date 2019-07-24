package io.github.portlek.reflection;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

class LoggerOfTest {

    private static final Logger LOGGER = new LoggerOf(LoggerOfTest.class);

    @Test
    void logging() {
        LOGGER.info("logging() -> Test succeed!");
    }

    @Test
    void prefix() {
        MatcherAssert.assertThat(
            "Logger name isn't equal to simple name of the class!",
            LOGGER.getName(),
            CoreMatchers.equalTo("LoggerOfTest")
        );
    }

    @Test
    void logLevel() {
        LOGGER.setLevel(Level.INFO);
        MatcherAssert.assertThat(
            "Logger level is not equal the chosen level!",
            LOGGER.getLevel(),
            CoreMatchers.equalTo(Level.INFO)
        );
    }

}