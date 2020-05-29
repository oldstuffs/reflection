package io.github.portlek.reflection.clazz;

import io.github.portlek.reflection.RefClass;
import org.hamcrest.core.IsEqual;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

final class ClassOfTest {

    private static final RefClass<ClassOfTest.TestClass> CLASS = new ClassOf<>(ClassOfTest.TestClass.class);

    @Test
    void getRealClass() {
        new Assertion<>(
            "Class is not equal to real class!",
            ClassOfTest.CLASS.getRealClass(),
            new IsEqual<>(ClassOfTest.TestClass.class)
        ).affirm();
    }

    @Test
    void isInstance() {
        new Assertion<>(
            "Class is not instance of the object",
            ClassOfTest.CLASS.isInstance(new ClassOfTest.TestTestClass("Hasan", 21)),
            new IsTrue()
        ).affirm();
    }

    @Test
    void getMethod() {
        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.getMethod("voidMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.getMethod("returnStringMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.getMethod("voidParameterMethod", String.class, Integer.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.getMethod("returnIntegerParameterMethod", String.class, Integer.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.getPrimitiveMethod("voidPrimitiveParameterMethod", String.class, Integer.class)
                .isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.getPrimitiveMethod("returnIntPrimitiveParameterMethod", String.class,
                Integer.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findMethodByParameter() {
        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByParameter().isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByParameter(String.class, Integer.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByParameter(String.class, int.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findPrimitiveMethodByParameter() {
        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findPrimitiveMethodByParameter().isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findPrimitiveMethodByParameter(String.class, Integer.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findPrimitiveMethodByParameter(String.class, int.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findMethodByName() {
        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByName("voidMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByName("returnStringMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByName("voidParameterMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByName("returnIntegerParameterMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByName("voidPrimitiveParameterMethod").isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByName("returnIntPrimitiveParameterMethod").isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findMethodByReturnType() {
        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByReturnType(void.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByReturnType(String.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByReturnType(Integer.class).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByReturnType(int.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findMethodByReturnTypeAsRefClass() {
        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByReturnType(new ClassOf<>(void.class)).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByReturnType(new ClassOf<>(String.class)).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByReturnType(new ClassOf<>(Integer.class)).isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find method",
            ClassOfTest.CLASS.findMethodByReturnType(new ClassOf<>(int.class)).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void getPrimitiveConstructor() {
        new Assertion<>(
            "Cannot find constructor!",
            ClassOfTest.CLASS.getPrimitiveConstructor(String.class, Integer.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findConstructor() {
        new Assertion<>(
            "Cannot find constructor!",
            ClassOfTest.CLASS.findConstructor(2).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void getConstructor() {
        new Assertion<>(
            "Cannot find constructor!",
            ClassOfTest.CLASS.getConstructor().isPresent(),
            new IsTrue()
        ).affirm();

        new Assertion<>(
            "Cannot find constructor!",
            ClassOfTest.CLASS.getConstructor(String.class, int.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void getField() {
        new Assertion<>(
            "Cannot find field!",
            ClassOfTest.CLASS.getField("text").isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findFieldFromClass() {
        new Assertion<>(
            "Cannot find field!",
            ClassOfTest.CLASS.findField(String.class).isPresent(),
            new IsTrue()
        ).affirm();
    }

    @Test
    void findFieldFromRefClass() {
        new Assertion<>(
            "Cannot find field!",
            ClassOfTest.CLASS.findField(new ClassOf<>(String.class)).isPresent(),
            new IsTrue()
        ).affirm();
    }

    private static class TestTestClass extends ClassOfTest.TestClass {

        TestTestClass(final String text, final int age) {
            super(text, age);
        }

    }

    private static class TestClass {

        @NotNull
        private final String text;

        private final int age;

        TestClass(@NotNull final String text, final int age) {
            this.text = text;
            this.age = age;
        }

        TestClass() {
            this("Hasan", 21);
        }

        private void voidMethod() {

        }

        private String returnStringMethod() {
            return "Called Return Method!";
        }

        private void voidParameterMethod(final String param1, final Integer param2) {

        }

        private Integer returnIntegerParameterMethod(final String param1, final Integer param2) {
            return 1;
        }

        private void voidPrimitiveParameterMethod(final String param1, final int param2) {

        }

        private int returnIntPrimitiveParameterMethod(final String param1, final int param2) {
            return 1;
        }

    }

}