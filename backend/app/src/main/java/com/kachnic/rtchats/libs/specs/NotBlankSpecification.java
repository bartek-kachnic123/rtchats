package com.kachnic.rtchats.libs.specs;

import com.kachnic.rtchats.libs.ddd.DomainValidate;

public enum NotBlankSpecification implements Specification<String> {
    INSTANCE;

    public static NotBlankSpecification of() {
        return INSTANCE;
    }

    @Override
    public void check(final String candidate, final String paramName) {
        DomainValidate.assertNotBlank(candidate, paramName);
    }
}
