package com.kachnic.rtchats.libs.ddd.specs;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.ddd.exceptions.MissingArgumentException;

public enum NotBlankSpecification implements Specification<String> {
    INSTANCE;

    public static NotBlankSpecification of() {
        return INSTANCE;
    }

    @Override
    public void check(final String candidate, final String paramName) {
        DomainValidate.ifBlank(candidate).thenThrow(() -> new MissingArgumentException(paramName));
    }
}
