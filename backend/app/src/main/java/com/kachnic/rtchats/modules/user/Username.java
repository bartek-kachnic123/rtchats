package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.specs.Specification;

record Username(String value) {

    /* package */ static Username of(final String value) {
        return withSpec(value, UsernameSpecification.getDefault());
    }

    /* package */ static Username withSpec(final String value, final Specification<String> spec) {
        spec.check(value, Username.class.getSimpleName());
        return new Username(value);
    }
}
