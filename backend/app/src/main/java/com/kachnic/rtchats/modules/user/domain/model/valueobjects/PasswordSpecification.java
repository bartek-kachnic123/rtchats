package com.kachnic.rtchats.modules.user.domain.model.valueobjects;

import com.kachnic.rtchats.libs.specs.MaxLengthSpecification;
import com.kachnic.rtchats.libs.specs.NotBlankSpecification;
import com.kachnic.rtchats.libs.specs.Specification;

final class PasswordSpecification {
    private static final int MAX_LENGTH = 255;
    private static final Specification<String> DEFAULT =
            NotBlankSpecification.of().and(MaxLengthSpecification.of(MAX_LENGTH));

    private PasswordSpecification() {}

    /* package */ static Specification<String> getDefault() {
        return DEFAULT;
    }
}
