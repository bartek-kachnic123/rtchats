package com.kachnic.rtchats.libs.exceptions;

import com.kachnic.rtchats.libs.exceptions.service.ErrorCode;
import java.io.Serial;

public class ArgumentInvalidFormatException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String paramName;
    private final String invalidValue;

    public ArgumentInvalidFormatException(final ErrorCode code, final String paramName, final String invalidValue) {
        super(code);
        this.paramName = paramName;
        this.invalidValue = invalidValue;
    }

    public String getParamName() {
        return paramName;
    }

    public String getInvalidValue() {
        return invalidValue;
    }
}
