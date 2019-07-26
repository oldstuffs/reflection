package io.github.portlek.reflection.parameter;

import io.github.portlek.reflection.RefParameterized;
import org.cactoos.map.MapEntry;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

import java.util.Map;

class ParameterizedOfTest {

    @Test
    @SuppressWarnings("unchecked")
    void apply() throws Exception {
        String text = "test";
        int x = 1;
        final RefParameterized<Map.Entry<Class<String>, Class<Integer>>> parameter = new ParameterizedOf<>(text, x);

        new Assertion<>(
            "Function doesn't give right classes and objects",
            parameter.apply(classes -> new MapEntry<>((Class<String>) classes[0], (Class<Integer>) classes[1])),
            new IsEqual<>(new MapEntry<>(String.class, Integer.class))
        ).affirm();
    }

}