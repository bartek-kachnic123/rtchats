package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.specs.Specification;

record Email(String value) {
    /* package */ static Email of(final String value) {
        return withSpec(value, EmailSpecification.getDefault());
    }

    /* package */ static Email withSpec(final String value, final Specification<String> spec) {
        spec.check(value, Email.class.getSimpleName());
        return new Email(value);
    }
}
