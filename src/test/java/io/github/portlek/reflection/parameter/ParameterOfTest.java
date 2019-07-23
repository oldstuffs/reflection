package io.github.portlek.reflection.parameter;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParameterOfTest {

    @Test
    void of() {
        int x = 1;
        int y = 2;
        Integer xx = 1;
        Integer yy = 2;
        MatcherAssert.assertThat(
            "Primitive method not working!",
            ParameterOf.of(true, x, y, xx, yy),
            CoreMatchers.equalTo(new Class[] {int.class, int.class})
        );
    }
}