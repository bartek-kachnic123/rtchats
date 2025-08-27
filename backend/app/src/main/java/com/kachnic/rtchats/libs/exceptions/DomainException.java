package com.kachnic.rtchats.libs.exceptions;

import com.kachnic.rtchats.libs.exceptions.service.ErrorCode;
import java.io.Serial;
import java.util.*;

public abstract class DomainException extends RuntimeException implements LocalizableMessage {

    @Serial
    private static final long serialVersionUID = 1L;

    private final ErrorCode errorCode;
    private final List<Object> args;

    protected DomainException(final ErrorCode errorCode) {
        this(errorCode, List.of(), null);
    }

    protected DomainException(final ErrorCode errorCode, final Throwable cause) {
        this(errorCode, List.of(), cause);
    }

    protected DomainException(final ErrorCode errorCode, final List<Object> args) {
        this(errorCode, args, null);
    }

    protected DomainException(final ErrorCode errorCode, final List<Object> args, final Throwable cause) {
        super("[" + errorCode.getValue() + "] args=" + args, cause);
        this.errorCode = errorCode;
        this.args = args;
    }

    @Override
    public final String getCode() {
        return errorCode.getValue();
    }

    @Override
    public final List<Object> getArgs() {
        return List.copyOf(args);
    }
}
