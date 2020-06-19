package io.github.portlek.reflection.constructor;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.RefConstructed;
import io.github.portlek.reflection.clazz.ClassOf;
import java.util.Optional;
import org.hamcrest.core.IsInstanceOf;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

final class ConstructorOfTest {

    @Test
    void create() throws NoSuchMethodException {
        final RefClass<ConstructorOfTest.ConstructorTest> refClass = new ClassOf<>(ConstructorOfTest.ConstructorTest.class);
        final Optional<RefConstructed<ConstructorOfTest.ConstructorTest>> optional =
            refClass.primitiveConstructor(String.class, int.class);
        new Assertion<>(
            "Cannot created object of the Constructed!",
            optional
                .orElseThrow(() ->
                    new NoSuchMethodException("Cannot find constructor!"))
                .create("Hasan", 21)
                .orElseThrow(() ->
                    new NoSuchMethodException("Couldn't create object from constructor!")),
            new IsInstanceOf(ConstructorOfTest.ConstructorTest.class)
        ).affirm();
    }

    private static class ConstructorTest {

        @NotNull
        private final String name;

        private final int age;

        private ConstructorTest(@NotNull final String text, final int age) {
            this.name = text;
            this.age = age;
        }

    }

}
