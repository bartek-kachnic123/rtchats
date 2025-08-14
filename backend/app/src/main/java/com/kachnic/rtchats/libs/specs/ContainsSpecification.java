package com.kachnic.rtchats.libs.specs;

import com.kachnic.rtchats.libs.ddd.DomainValidate;
import com.kachnic.rtchats.libs.exceptions.ArgumentInvalidFormatException;
import java.util.regex.Pattern;

public final class ContainsSpecification implements Specification<String> {
    private final Pattern pattern;

    private ContainsSpecification(final Pattern pattern) {
        this.pattern = pattern;
    }

    public static ContainsSpecification of(final Pattern pattern) {
        return new ContainsSpecification(pattern);
    }

    @Override
    public void check(final String candidate, final String paramName) {
        DomainValidate.assertTrue(
                containsPattern(candidate), () -> new ArgumentInvalidFormatException(paramName, candidate));
    }

    private boolean containsPattern(final String value) {
        return pattern.matcher(value).find();
    }
}
