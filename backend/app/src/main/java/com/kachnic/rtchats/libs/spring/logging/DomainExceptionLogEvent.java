package com.kachnic.rtchats.libs.spring.logging;

import com.kachnic.rtchats.libs.exceptions.DomainException;

public class DomainExceptionLogEvent extends ExceptionLogEvent {

    public DomainExceptionLogEvent(final DomainException exception) {
        super(exception);
    }
}
