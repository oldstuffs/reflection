package io.github.portlek.reflection;

import org.cactoos.iterable.IterableOf;
import org.cactoos.list.Mapped;
import org.cactoos.text.TextOf;
import org.jetbrains.annotations.NotNull;

import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerOf extends Logger {

    private final String prefix;

    public LoggerOf(@NotNull final Class<?>... classes) {
        super(
            new TextOf(
                new Mapped<>(
                    Class::getSimpleName,
                    new IterableOf<>(classes)
                )
            ).toString(),
            null
        );
        this.prefix = "[" + new TextOf(
            new Mapped<>(
                Class::getSimpleName,
                new IterableOf<>(classes)
            )
        ).toString() + "] ";
    }

    @Override
    public void log(LogRecord logRecord) {
        logRecord.setMessage(prefix + logRecord.getMessage());
        super.log(logRecord);
    }

}
