package io.github.portlek.reflection.field;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.clazz.ClassOf;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

final class FieldOfTest {

    private static final Object FIELD_TEST = new FieldOfTest.FieldTest();

    private final RefClass<FieldOfTest.FieldTest> refClass = new ClassOf<>(FieldOfTest.FieldTest.class);

    @Test
    void get() throws Throwable {
        new Assertion<>(
            "Couldn't get the field",
            this.refClass.getField("text")
                .orElseThrow(() ->
                    new NoSuchFieldException("Cannot find field!"))
                .of(FieldOfTest.FIELD_TEST)
                .get()
                .orElseThrow(() ->
                    new NoSuchFieldException("Cannot find field!")),
            new IsEqual<>("Test Text")
        ).affirm();

        new Assertion<>(
            "Couldn't get the field",
            this.refClass.getField("TEXT")
                .orElseThrow(() ->
                    new NoSuchFieldException("Cannot find field!"))
                .of(FieldOfTest.FIELD_TEST)
                .get()
                .orElseThrow(() ->
                    new NoSuchFieldException("Cannot find field!")),
            new IsEqual<>("Static Test Text")
        ).affirm();
    }

    @Test
    void set() throws NoSuchFieldException {
        this.refClass.getField("text")
            .orElseThrow(() ->
                new NoSuchFieldException("Cannot find field!"))
            .of(FieldOfTest.FIELD_TEST)
            .set("Edited Test Text");

        new Assertion<>(

            "Couldn't set the field!",
            this.refClass.getField("text")
                .orElseThrow(() ->
                    new NoSuchFieldException("Cannot find field!"))
                .of(FieldOfTest.FIELD_TEST)
                .get()
                .orElseThrow(() ->
                    new NoSuchFieldException("Cannot find field!")),
            new IsEqual<>("Edited Test Text")
        ).affirm();
    }

    private static final class FieldTest {

        private static final String TEXT = "Static Test Text";

        private final String text = "Test Text";

    }

}