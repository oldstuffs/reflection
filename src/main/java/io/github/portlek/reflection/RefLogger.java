package io.github.portlek.reflection;

import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class RefLogger extends Logger {

    private final String className;

    public RefLogger(Class<?> clazz) {
        super(clazz.getSimpleName(), null);
        this.className = "[" + clazz.getSimpleName() + "] ";
    }

    public RefLogger(Object obj) {
        this(obj.getClass());
    }

    @Override
    public void log(LogRecord logRecord) {
        logRecord.setMessage(className + logRecord.getMessage());
        super.log(logRecord);
    }

}
