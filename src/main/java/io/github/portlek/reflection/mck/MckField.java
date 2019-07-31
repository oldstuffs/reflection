package io.github.portlek.reflection.mck;

import io.github.portlek.reflection.RefField;
import io.github.portlek.reflection.RefFieldExecuted;
import org.jetbrains.annotations.NotNull;

public class MckField implements RefField {
    @Override
    public void set(@NotNull Object value) {
    }
    @NotNull
    @Override
    public Object get(@NotNull Object fallback) {
        return fallback;
    }
    @NotNull
    @Override
    public RefFieldExecuted of(@NotNull Object object) {
        return new RefFieldExecuted() {
            @Override
            public void set(@NotNull Object value) {
            }
            @NotNull
            @Override
            public Object get(@NotNull Object fallback) {
                return fallback;
            }
        };
    }
}
