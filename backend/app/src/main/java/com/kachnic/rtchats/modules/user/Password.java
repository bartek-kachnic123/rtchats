package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.specs.Specification;

record Password(String value) {
    /* package */ static Password of(final String value) {
        return withSpec(value, PasswordSpecification.getDefault());
    }
    /* package */ static Password withSpec(final String value, final Specification<String> spec) {
        spec.check(value, Password.class.getSimpleName());
        return new Password(value);
    }
}
