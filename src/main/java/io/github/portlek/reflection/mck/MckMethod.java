package io.github.portlek.reflection.mck;

import io.github.portlek.reflection.RefMethod;
import io.github.portlek.reflection.RefMethodExecuted;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MckMethod implements RefMethod {
    @NotNull
    @Override
    public RefMethodExecuted of(@NotNull Object object) {
        return new MckMethodExecuted();
    }

    @Nullable
    @Override
    public Object call(@NotNull Object... parameters) {
        return null;
    }

    private static class MckMethodExecuted implements RefMethodExecuted {
        @Nullable
        @Override
        public Object call(@NotNull Object... parameters) {
            return null;
        }
    }

}
