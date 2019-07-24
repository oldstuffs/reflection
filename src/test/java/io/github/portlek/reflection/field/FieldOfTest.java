package io.github.portlek.reflection.field;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.clazz.ClassOf;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

class FieldOfTest {

    private final static Object FIELD_TEST = new FieldTest();

    private final RefClass refClass = new ClassOf(FieldTest.class);

    @Test
    void get() {
        new Assertion<>(
            "Couldn't get the field",
            refClass.getField("text").of(FIELD_TEST).get(),
            new IsEqual<>("Test Text")
        ).affirm();

        new Assertion<>(
            "Couldn't get the field",
            refClass.getField("TEXT").of(FIELD_TEST).get(),
            new IsEqual<>("Static Test Text")
        ).affirm();
    }

    @Test
    void set() {
        refClass.getField("text").of(FIELD_TEST).set("Edited Test Text");

        new Assertion<>(

            "Couldn't set the field!",
            refClass.getField("text").of(FIELD_TEST).get(),
            new IsEqual<>("Edited Test Text")
        ).affirm();
    }

    private static final class FieldTest {

        private static final String TEXT = "Static Test Text";
        private final String text = "Test Text";

    }

}