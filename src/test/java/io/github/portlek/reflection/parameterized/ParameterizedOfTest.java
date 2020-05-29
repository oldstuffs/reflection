package io.github.portlek.reflection.parameterized;

import io.github.portlek.reflection.RefParameterized;
import java.util.Map;
import java.util.Optional;
import org.cactoos.map.MapEntry;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

final class ParameterizedOfTest {

    @Test
    void apply() {
        final String text = "test";
        final int testnumber = 1;
        final RefParameterized<Map.Entry<Class<String>, Class<Integer>>> parameter = new ParameterizedOf<>(
            RuntimeException::new, text, testnumber);

        new Assertion<>(
            "Function doesn't give right classes and objects",
            parameter.apply(classes ->
                Optional.of(new MapEntry<>((Class<String>) classes[0], (Class<Integer>) classes[1])))
                .orElseThrow(() ->
                    new RuntimeException("Something's wrong!")),
            new IsEqual<>(new MapEntry<>(String.class, Integer.class))
        ).affirm();
    }

}