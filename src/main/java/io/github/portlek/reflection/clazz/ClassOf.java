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
    public boolean isInstance(Object object) {
        return clazz.isInstance(object);
    }

    @NotNull
    @Override
    public RefMethod getMethod(String name, Object... types) {
        return null;
    }

    @NotNull
    @Override
    public RefConstructed getConstructor(Object... types) {
        return null;
    }

    @NotNull
    @Override
    public RefMethod findMethod(Object... types) {
        return null;
    }

    @NotNull
    @Override
    public RefMethod findMethodByName(String... names) {
        return null;
    }

    @NotNull
    @Override
    public RefMethod findMethodByReturnType(RefClass type) {
        return null;
    }

    @NotNull
    @Override
    public RefMethod findMethodByReturnType(Class type) {
        return null;
    }

    @NotNull
    @Override
    public RefConstructed findConstructor(int number) {
        return null;
    }

    @NotNull
    @Override
    public RefField getField(String name) {
        return null;
    }

    @NotNull
    @Override
    public RefField findField(RefClass type) {
        return null;
    }

    @NotNull
    @Override
    public RefField findField(Class type) {
        return null;
    }

}
