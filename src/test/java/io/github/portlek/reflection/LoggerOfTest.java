package io.github.portlek.reflection;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

class LoggerOfTest {

    @Test
    void log() {
        MatcherAssert.assertThat(
            "Log test passed!",
            new LoggerOf(LoggerOfTest.class).getName(),
            CoreMatchers.equalTo("LoggerOfTest")
        );
    }

}