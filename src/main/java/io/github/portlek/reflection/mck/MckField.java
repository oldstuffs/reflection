package io.github.portlek.reflection.mck;

import io.github.portlek.reflection.RefField;
import io.github.portlek.reflection.RefFieldExecuted;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MckField implements RefField {

    @NotNull
    @Override
    public RefFieldExecuted of(@NotNull Object object) {
        return new RefFieldExecuted() {
            @Override
            public void set(@NotNull Object value) {
            }

            @Nullable
            @Override
            public Object get() {
                return null;
            }
        };
    }

}
