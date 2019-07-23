package io.github.portlek.reflection.method;

import io.github.portlek.reflection.RefMethod;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

public class MethodOf implements RefMethod {

    private final Method method;

    public MethodOf(@NotNull final Method method) {
        this.method = method;
    }

}
