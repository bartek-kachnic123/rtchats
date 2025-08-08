package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.specs.Specification;

public record Email(String value) {
    public static Email of(final String value) {
        return withSpec(value, EmailSpecification.getDefault());
    }

    public static Email withSpec(final String value, final Specification<String> spec) {
        spec.check(value, Email.class.getSimpleName());
        return new Email(value);
    }
}
