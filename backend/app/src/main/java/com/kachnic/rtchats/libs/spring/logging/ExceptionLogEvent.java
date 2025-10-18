package com.kachnic.rtchats.libs.spring.logging;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.kachnic.rtchats.libs.exceptions.ExceptionBase;

import lombok.Getter;

public class ExceptionLogEvent {

    private final String exceptionName;
    private final String message;

    @Getter
    private final String stackTrace;

    private static final String LINE_SEPARATOR = "\n";

    public ExceptionLogEvent(final ExceptionBase exception) {
        this.exceptionName = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.stackTrace = Arrays.stream(exception.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    @Override
    public String toString() {
        return exceptionName + ": " + message;
    }
}
