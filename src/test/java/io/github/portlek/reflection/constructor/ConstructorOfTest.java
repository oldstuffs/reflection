package io.github.portlek.reflection.constructor;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.RefConstructed;
import io.github.portlek.reflection.clazz.ClassOf;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

class ConstructorOfTest {

    @Test
    void create() {
        final RefClass refClass = new ClassOf(ConstructorTest.class);
        final RefConstructed refConstructed = refClass.getPrimitiveConstructor(getClass(), String.class, int.class);

        new Assertion<>(
            "Cannot created object from the Constructed!",
            refConstructed.create(this, "Hasan", 21),
            new IsInstanceOf(ConstructorTest.class)
        ).affirm();
    }

    private class ConstructorTest {

        private final String name;
        private final int age;

        public ConstructorTest(String text, int age) {
            this.name = text;
            this.age = age;
        }

    }

}