package com.kachnic.rtchats.libs.spring.logging;

import com.kachnic.rtchats.libs.exceptions.DomainException;

public class DomainLogEvent extends ExceptionLogEvent {

    public DomainLogEvent(final DomainException exception) {
        super(exception);
    }
}
