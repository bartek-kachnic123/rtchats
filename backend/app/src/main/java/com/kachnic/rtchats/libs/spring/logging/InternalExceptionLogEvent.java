package com.kachnic.rtchats.libs.spring.logging;

import com.kachnic.rtchats.libs.exceptions.InternalException;

public class InternalExceptionLogEvent extends ExceptionLogEvent {

    public InternalExceptionLogEvent(final InternalException exception) {
        super(exception);
    }
}
