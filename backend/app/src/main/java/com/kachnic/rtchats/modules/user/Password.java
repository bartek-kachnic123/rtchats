package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.specs.Specification;

public record Password(String value) {
    public static Password of(final String value) {
        return withSpec(value, PasswordSpecification.getDefault());
    }

    public static Password withSpec(final String value, final Specification<String> spec) {
        spec.check(value, Password.class.getSimpleName());
        return new Password(value);
    }
}
