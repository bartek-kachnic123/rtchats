package com.kachnic.rtchats.libs.exceptions;

import com.kachnic.rtchats.libs.exceptions.service.ArgumentErrorCode;
import java.io.Serial;
import java.util.List;

public class ArgumentOutOfRangeException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ArgumentOutOfRangeException(final String paramName, final int actual, final int min, final int max) {
        super(ArgumentErrorCode.OUT_OF_RANGE, List.of(paramName, actual, min, max));
    }
}
