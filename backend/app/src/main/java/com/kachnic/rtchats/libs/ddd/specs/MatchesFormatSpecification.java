package com.kachnic.rtchats.libs.ddd.specs;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.ddd.exceptions.ArgumentInvalidFormatException;
import java.util.regex.Pattern;

public record MatchesFormatSpecification(Pattern pattern) implements Specification<String> {
    public static MatchesFormatSpecification of(final Pattern pattern) {
        return new MatchesFormatSpecification(pattern);
    }

    @Override
    public void check(final String value, final String paramName) {
        DomainValidate.ifTrue(isInvalidFormat(value))
                .thenThrow(() -> new ArgumentInvalidFormatException(paramName, value));
    }

    private boolean isInvalidFormat(final String value) {
        return !pattern.matcher(value).matches();
    }
}
