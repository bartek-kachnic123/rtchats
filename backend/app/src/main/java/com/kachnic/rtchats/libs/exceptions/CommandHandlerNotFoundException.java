package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;

public class CommandHandlerNotFoundException extends InternalException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CommandHandlerNotFoundException(final String message) {
        super(message);
    }
}
