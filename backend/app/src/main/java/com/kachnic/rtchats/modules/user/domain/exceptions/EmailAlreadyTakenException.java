package com.kachnic.rtchats.modules.user.domain.exceptions;

import java.io.Serial;
import java.util.List;

public class EmailAlreadyTakenException extends UserException {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String FIELD_NAME = "email";

    public EmailAlreadyTakenException(final String email) {
        super(UserErrorCode.EMAIL_TAKEN, List.of(email));
    }

    public static String getFieldName() {
        return FIELD_NAME;
    }
}
