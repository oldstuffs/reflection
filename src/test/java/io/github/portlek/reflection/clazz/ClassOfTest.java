package io.github.portlek.reflection.clazz;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.RefConstructed;
import io.github.portlek.reflection.constructor.ConstructorOf;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

class ClassOfTest {

    private static final RefClass CLASS = new ClassOf(TestClass.class);

    @Test
    void findConstructor() {
        MatcherAssert.assertThat(
            "Cannot find constructor!",
            CLASS.findConstructor(1),
            CoreMatchers.instanceOf(ConstructorOf.class)
        );
    }

    @Test
    void getConstructor() {
        MatcherAssert.assertThat(
            "Cannot find constructor!",
            CLASS.getConstructor(1),
            CoreMatchers.instanceOf(ConstructorOf.class)
        );
    }

    private static final class TestClass {

        private final int integer;

        public TestClass(Integer integer) {
            this.integer = integer;
        }

    }

}