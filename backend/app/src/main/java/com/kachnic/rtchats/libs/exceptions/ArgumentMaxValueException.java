package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;
import java.util.List;

import com.kachnic.rtchats.libs.exceptions.codes.ArgumentErrorCode;

public class ArgumentMaxValueException extends DomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ArgumentMaxValueException(final String paramName, final int actual, final int max) {
        super(ArgumentErrorCode.MAX_VALUE, List.of(paramName, actual, max));
    }
}
