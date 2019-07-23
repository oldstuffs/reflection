package io.github.portlek.reflection.clazz;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.RefConstructed;
import io.github.portlek.reflection.RefField;
import io.github.portlek.reflection.RefMethod;
import io.github.portlek.reflection.mck.MckConstructed;
import io.github.portlek.reflection.mck.MckField;
import io.github.portlek.reflection.mck.MckMethod;
import org.jetbrains.annotations.NotNull;

public class ClassOf implements RefClass {

    @NotNull
    private final Class<?> clazz;

    public ClassOf(@NotNull final Class<?> clazz) {
        this.clazz = clazz;
    }

    public ClassOf(@NotNull final String className) throws ClassNotFoundException {
        this(Class.forName(className));
    }

    @Override
    public boolean isInstance(@NotNull final Object object) {
        return clazz.isInstance(object);
    }

    @NotNull
    @Override
    public RefMethod getMethod(@NotNull String name, @NotNull final Object... types) {
        return new MckMethod();
    }

    @NotNull
    @Override
    public RefConstructed getConstructor(@NotNull final Object... types) {
        return new MckConstructed();
    }

    @NotNull
    @Override
    public RefMethod findMethod(@NotNull final Object... types) {
        return new MckMethod();
    }

    @NotNull
    @Override
    public RefMethod findMethodByName(@NotNull final String... names) {
        return new MckMethod();
    }

    @NotNull
    @Override
    public RefMethod findMethodByReturnType(@NotNull final RefClass type) {
        return new MckMethod();
    }

    @NotNull
    @Override
    public RefMethod findMethodByReturnType(@NotNull final Class type) {
        return new MckMethod();
    }

    @NotNull
    @Override
    public RefConstructed findConstructor(final int number) {
        return new MckConstructed();
    }

    @NotNull
    @Override
    public RefField getField(@NotNull final String name) {
        return new MckField();
    }

    @NotNull
    @Override
    public RefField findField(@NotNull final RefClass type) {
        return new MckField();
    }

    @NotNull
    @Override
    public RefField findField(@NotNull final Class type) {
        return new MckField();
    }

}
