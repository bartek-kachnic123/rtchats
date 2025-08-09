package com.kachnic.rtchats.libs.ddd;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import java.util.function.Supplier;

public final class DomainValidate {

    private DomainValidate() {}

    public static <T> Validation ifNull(final T value) {
        return new Validation(value == null);
    }

    public static Validation ifBlank(final String value) {
        return new Validation(value == null || value.isBlank());
    }

    public static Validation ifTrue(final boolean condition) {
        return new Validation(condition);
    }

    public static final class Validation {
        private final boolean shouldThrow;

        private Validation(final boolean shouldThrow) {
            this.shouldThrow = shouldThrow;
        }

        public void thenThrow(final Supplier<? extends DomainException> exceptionSupplier) {
            if (shouldThrow) {
                throw exceptionSupplier.get();
            }
        }
    }
}
