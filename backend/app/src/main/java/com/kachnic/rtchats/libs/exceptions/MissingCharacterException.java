package com.kachnic.rtchats.libs.exceptions;

import com.kachnic.rtchats.libs.exceptions.codes.ErrorCode;
import java.io.Serial;
import java.util.List;

public class MissingCharacterException extends DomainException {

    @Serial
    private static final long serialVersionUID = 1L;

    public MissingCharacterException(final ErrorCode code, final String paramName, final String invalidValue) {
        super(code, List.of(paramName, invalidValue));
    }
}
