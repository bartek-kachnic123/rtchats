package com.kachnic.rtchats.modules.user.domain.exceptions;

import com.kachnic.rtchats.libs.exceptions.LocalizableMessage;
import java.io.Serial;

public class EmailAlreadyTakenException extends UserException implements LocalizableMessage {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String FIELD_NAME = "email";
    private static final String MESSAGE_PREFIX = "Email already taken: ";
    private static final String CODE = "user.email.already-taken";
    private final String email;

    public EmailAlreadyTakenException(final String email) {
        super(MESSAGE_PREFIX + email);
        this.email = email;
    }

    @Override
    public String getCode() {
        return CODE;
    }

    @Override
    public Object[] getArgs() {
        return new Object[] {email};
    }

    public String getEmail() {
        return email;
    }

    public static String getFieldName() {
        return FIELD_NAME;
    }
}
