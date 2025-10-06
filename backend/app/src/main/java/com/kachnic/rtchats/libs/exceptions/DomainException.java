package com.kachnic.rtchats.libs.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kachnic.rtchats.libs.exceptions.codes.ErrorCode;

public class DomainException extends RuntimeException implements LocalizableMessage {

    @Serial
    private static final long serialVersionUID = 1L;

    private final ErrorCode code;
    private final List<Serializable> args;

    public DomainException(final String message, final ErrorCode code) {
        this(message, code, List.of());
    }

    public DomainException(final String message, final ErrorCode code, final List<Serializable> args) {
        this(message, code, args, null);
    }

    public DomainException(final ErrorCode code, final Throwable cause) {
        this(code, List.of(), cause);
    }

    public DomainException(final ErrorCode code, final List<Serializable> args, final Throwable cause) {
        this(null, code, args, cause);
    }

    public DomainException(
            final String message, final ErrorCode code, final List<Serializable> args, final Throwable cause) {
        super(message, cause);
        this.code = code;
        this.args = makeListSerializable(args);
    }

    protected <E> List<E> makeListSerializable(final List<E> list) {
        return new ArrayList<>(list);
    }

    public String getName() {
        final String className = this.getClass().getSimpleName();
        return className.endsWith("Exception")
                ? className.substring(0, className.length() - "Exception".length())
                : className;
    }

    @Override
    public final String getCode() {
        return code.getValue();
    }

    @Override
    public final List<Object> getArgs() {
        return Collections.unmodifiableList(args);
    }
}
