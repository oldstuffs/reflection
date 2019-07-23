package io.github.portlek.reflection.mck;

import io.github.portlek.reflection.RefClass;
import io.github.portlek.reflection.RefConstructed;
import io.github.portlek.reflection.RefField;
import io.github.portlek.reflection.RefMethod;
import org.jetbrains.annotations.NotNull;

public class MckClass implements RefClass {

    @Override
    public boolean isInstance(@NotNull Object object) {
        return false;
    }

    @NotNull
    @Override
    public RefMethod getMethod(@NotNull String name, @NotNull Object... types) {
        return new MckMethod();
    }

    @NotNull
    @Override
    public RefConstructed getConstructor(@NotNull Object... types) {
        return new MckConstructed();
    }

    @NotNull
    @Override
    public RefMethod findMethod(@NotNull Object... types) {
        return new MckMethod();
    }

    @NotNull
    @Override
    public RefMethod findMethodByName(@NotNull String... names) {
        return new MckMethod();
    }

    @NotNull
    @Override
    public RefMethod findMethodByReturnType(@NotNull RefClass type) {
        return new MckMethod();
    }

    @NotNull
    @Override
    public RefMethod findMethodByReturnType(@NotNull Class type) {
        return new MckMethod();
    }

    @NotNull
    @Override
    public RefConstructed findConstructor(int number) {
        return new MckConstructed();
    }

    @NotNull
    @Override
    public RefField getField(@NotNull String name) {
        return new MckField();
    }

    @NotNull
    @Override
    public RefField findField(@NotNull RefClass type) {
        return new MckField();
    }

    @NotNull
    @Override
    public RefField findField(@NotNull Class type) {
        return new MckField();
    }

}
