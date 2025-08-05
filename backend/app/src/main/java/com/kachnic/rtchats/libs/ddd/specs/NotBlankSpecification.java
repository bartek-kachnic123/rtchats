package com.kachnic.rtchats.libs.ddd.specs;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.ddd.exceptions.MissingArgumentException;

public final class NotBlankSpecification implements Specification<String> {
    private static final NotBlankSpecification INSTANCE = new NotBlankSpecification();

    private NotBlankSpecification() {}

    public static NotBlankSpecification of() {
        return INSTANCE;
    }

    @Override
    public void check(final String candidate, final String paramName) {
        DomainValidate.ifBlank(candidate).thenThrow(() -> new MissingArgumentException(paramName));
    }
}
