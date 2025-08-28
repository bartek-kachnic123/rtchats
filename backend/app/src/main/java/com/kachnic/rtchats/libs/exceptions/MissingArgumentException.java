package com.kachnic.rtchats.libs.exceptions;

import com.kachnic.rtchats.libs.exceptions.codes.ErrorCode;
import java.io.Serial;
import java.util.List;

public class MissingArgumentException extends DomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    public MissingArgumentException(final ErrorCode code, final String paramName) {
        super(code, List.of(paramName));
    }
}
