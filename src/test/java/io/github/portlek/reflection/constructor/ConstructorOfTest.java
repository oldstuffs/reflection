package io.github.portlek.reflection.constructor;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.RefConstructed;
import io.github.portlek.reflection.clazz.ClassOf;
import org.hamcrest.core.IsInstanceOf;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

class ConstructorOfTest {

    @Test
    void create() {
        final RefClass refClass = new ClassOf(ConstructorTest.class);
        final RefConstructed refConstructed = refClass.getPrimitiveConstructor(String.class, int.class);

        new Assertion<>(
            "Cannot created object from the Constructed!",
            refConstructed.create(
                new ConstructorTest("null", 0),
                "Hasan", 21
            ),
            new IsInstanceOf(ConstructorTest.class)
        ).affirm();
    }

    private static class ConstructorTest {

        @NotNull
        private final String name;
        private final int age;

        public ConstructorTest(@NotNull final String text, int age) {
            this.name = text;
            this.age = age;
        }

    }

}