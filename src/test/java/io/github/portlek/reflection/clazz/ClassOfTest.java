package io.github.portlek.reflection.clazz;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.constructor.ConstructorOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;

class ClassOfTest {

    private static final RefClass CLASS = new ClassOf(TestClass.class);

    @Test
    void findConstructor() {
        MatcherAssert.assertThat(
            "Cannot find constructor!",
            CLASS.findConstructor(2),
            new IsInstanceOf(ConstructorOf.class)
        );
    }

    @Test
    void getConstructor() {
        MatcherAssert.assertThat(
            "Cannot find constructor!",
            CLASS.getConstructor(String.class, int.class),
            new IsInstanceOf(ConstructorOf.class)
        );
    }

    @Test
    void getPrimitiveConstructor() {
        MatcherAssert.assertThat(
            "Cannot find constructor!",
            CLASS.getPrimitiveConstructor(String.class, Integer.class),
            new IsInstanceOf(ConstructorOf.class)
        );
    }

    private static final class TestClass {

        private final String text;
        private final int age;

        public TestClass(String text, int age) {
            this.text = text;
            this.age = age;
        }
    }

}