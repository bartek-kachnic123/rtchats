package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.ddd.exceptions.MissingArgumentException;
import java.util.regex.Pattern;

record Email(String value) {
    private static final EmailValidator VALIDATOR = new EmailValidator();
    private static final int MAX_LENGTH = 128;

    Email {
        DomainValidate.ifBlank(value).thenThrow(() -> new MissingArgumentException(Email.class.getSimpleName()));
        DomainValidate.ifTrue(VALIDATOR.exceedsMaxEmailLength(value))
                .thenThrow(() -> new InvalidEmailLengthException(MAX_LENGTH));
        DomainValidate.ifTrue(VALIDATOR.isInvalidEmail(value)).thenThrow(() -> new InvalidEmailFormatException(value));
    }

    /* package */ static Email of(final String value) {
        return new Email(value);
    }

    private static final class EmailValidator {
        private static final String EMAIL_PATTERN = ".+@.+\\..+";
        private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

        public boolean exceedsMaxEmailLength(final String email) {
            return email.length() > MAX_LENGTH;
        }

        public boolean isInvalidEmail(final String email) {
            return !PATTERN.matcher(email).matches();
        }
    }
}
