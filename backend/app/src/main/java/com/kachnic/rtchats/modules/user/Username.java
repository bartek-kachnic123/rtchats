package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.specs.Specification;

public record Username(String value) {
    public static Username of(final String value) {
        return withSpec(value, UsernameSpecification.getDefault());
    }

    public static Username withSpec(final String value, final Specification<String> spec) {
        spec.check(value, Username.class.getSimpleName());
        return new Username(value);
    }
}
