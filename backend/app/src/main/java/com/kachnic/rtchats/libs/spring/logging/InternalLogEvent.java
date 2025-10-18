package com.kachnic.rtchats.libs.spring.logging;

import com.kachnic.rtchats.libs.exceptions.InternalException;

public class InternalLogEvent extends ExceptionLogEvent {

    public InternalLogEvent(final InternalException exception) {
        super(exception);
    }
}
