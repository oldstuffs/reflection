package io.github.portlek.reflection.parameter;

import io.github.portlek.reflection.RefParameter;
import org.cactoos.map.MapEntry;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

import java.util.Map;

class ParameterOfTest {

    private final String text = "test";
    private final int x = 1;

    @Test
    void of() {
        new Assertion<>(
            "Primitive method not working!",
            ParameterOf.of(true, text, x),
            new IsEqual<>(new Class[] {String.class, int.class})
        ).affirm();
    }

    @Test
    void apply() throws Exception {
        final RefParameter<Map.Entry<String, Integer>> parameter = new ParameterOf<>(text, x);

        new Assertion<>(
            "Function doesn't give right classes and objects",
            parameter.apply((first, second) -> new MapEntry<>((String) second[0], (Integer) second[1])),
            new IsEqual<>(new MapEntry<>(text, x))
        ).affirm();
    }

}