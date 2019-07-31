package io.github.portlek.reflection;

import org.cactoos.iterable.IterableOf;
import org.cactoos.list.Mapped;
import org.cactoos.text.TextOf;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerOf extends Logger {

    @NotNull
    private final String prefix;

    private LoggerOf(@NotNull final String prefix) {
        super(prefix.replaceAll(", ", "#"), null);
        this.prefix = "[" + getName() + "] ";
        setParent(Logger.getLogger(prefix));
        setLevel(Level.ALL);
    }

    public LoggerOf(@NotNull final Class<?>... classes) {
        this(
            new TextOf(
                new Mapped<>(
                    Class::getSimpleName,
                    new IterableOf<>(classes)
                )
            ).toString()
        );
    }

    @Override
    public void log(@NotNull final LogRecord logRecord) {
        logRecord.setMessage(prefix + logRecord.getMessage());
        super.log(logRecord);
    }

}
