package io.github.portlek.reflection.clazz;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.RefConstructed;
import io.github.portlek.reflection.RefField;
import io.github.portlek.reflection.RefMethod;
import org.jetbrains.annotations.NotNull;

public class ClassOf implements RefClass {

    private final Class<?> clazz;

    public ClassOf(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean isInstance(@NotNull Object object) {
        return clazz.isInstance(object);
    }

    @NotNull
    @Override
    public RefMethod getMethod(@NotNull String name, @NotNull Object... types) {
        return null;
    }

    @NotNull
    @Override
    public RefConstructed getConstructor(@NotNull Object... types) {
        return null;
    }

    @NotNull
    @Override
    public RefMethod findMethod(@NotNull Object... types) {
        return null;
    }

    @NotNull
    @Override
    public RefMethod findMethodByName(@NotNull String... names) {
        return null;
    }

    @NotNull
    @Override
    public RefMethod findMethodByReturnType(@NotNull RefClass type) {
        return null;
    }

    @NotNull
    @Override
    public RefMethod findMethodByReturnType(@NotNull Class type) {
        return null;
    }

    @NotNull
    @Override
    public RefConstructed findConstructor(int number) {
        return null;
    }

    @NotNull
    @Override
    public RefField getField(@NotNull String name) {
        return null;
    }

    @NotNull
    @Override
    public RefField findField(@NotNull RefClass type) {
        return null;
    }

    @NotNull
    @Override
    public RefField findField(@NotNull Class type) {
        return null;
    }

}
