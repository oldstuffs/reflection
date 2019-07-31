package io.github.portlek.reflection.mck;

import io.github.portlek.reflection.RefMethod;
import io.github.portlek.reflection.RefMethodExecuted;
import org.jetbrains.annotations.NotNull;

public class MckMethod implements RefMethod {
    @NotNull
    @Override
    public RefMethodExecuted of(@NotNull Object object) {
        return new MckMethodExecuted();
    }
    @NotNull
    @Override
    public Object call(@NotNull Object fallback, @NotNull Object... parameters) {
        return fallback;
    }
    public static class MckMethodExecuted implements RefMethodExecuted {
        @NotNull
        @Override
        public Object call(@NotNull Object fallback, @NotNull Object... parameters) {
            return fallback;
        }
    }
}
