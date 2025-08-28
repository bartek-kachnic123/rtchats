package com.kachnic.rtchats.libs.specs;

import com.kachnic.rtchats.libs.ddd.DomainValidator;

public enum NotBlankSpecification implements Specification<String> {
    INSTANCE;

    public static NotBlankSpecification of() {
        return INSTANCE;
    }

    @Override
    public void check(final String candidate, final String paramName) {
        DomainValidator.assertNotBlank(candidate, paramName);
    }
}
