package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import com.kachnic.rtchats.libs.specs.Specification;

public record Username(String value) {
    public static Username of(final String value) {
        return withSpec(value, UsernameSpecification.getDefault());
    }

    public static Username withSpec(final String value, final Specification<String> spec) {
        spec.check(value, Username.class.getSimpleName());
        return new Username(value);
    }
}
