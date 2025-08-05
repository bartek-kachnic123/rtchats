package com.kachnic.rtchats.modules.user;

import com.kachnic.rtchats.libs.ddd.specs.NotBlankSpecification;
import com.kachnic.rtchats.libs.ddd.specs.Specification;

final class PasswordSpecification {
    private static final Specification<String> DEFAULT = NotBlankSpecification.of();

    private PasswordSpecification() {}

    /* package */ static Specification<String> getDefault() {
        return DEFAULT;
    }
}
