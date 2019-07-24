package io.github.portlek.reflection.constructor;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.RefConstructed;
import io.github.portlek.reflection.clazz.ClassOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;

class ConstructorOfTest {

    @Test
    void create() {
        final RefClass refClass = new ClassOf(ConstructorTest.class);
        final RefConstructed refConstructed = refClass.getPrimitiveConstructor(String.class, int.class);

        MatcherAssert.assertThat(
            "Cannot created object from the Constructed!",
            refConstructed.create("Hasan", 21),
            new IsInstanceOf(ConstructorTest.class)
        );
    }

    private static class ConstructorTest {

        private final String name;
        private final int age;

        public ConstructorTest(String text, int age) {
            this.name = text;
            this.age = age;
        }

    }

}