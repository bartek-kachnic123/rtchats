package com.kachnic.rtchats.libs.ddd;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import com.kachnic.rtchats.libs.exceptions.MissingArgumentException;
import com.kachnic.rtchats.libs.exceptions.codes.ArgumentErrorCode;

public final class DomainValidator {

    private static final String SPACE = " ";

    private DomainValidator() {}

    public static <T> void assertNonNull(final T obj, final String paramName) {
        if (obj == null) {
            throw new MissingArgumentException(ArgumentErrorCode.MISSING_NULL, paramName);
        }
    }

    public static <T> void assertNonNull(final T obj, final Supplier<String> paramNameSupplier) {
        if (obj == null) {
            throw new MissingArgumentException(ArgumentErrorCode.MISSING_NULL, paramNameSupplier.get());
        }
    }

    public static void assertNotBlank(final String str, final String paramName) {
        assertNonNull(str, paramName);
        if (str.isBlank()) {
            throw new MissingArgumentException(ArgumentErrorCode.MISSING_BLANK, paramName);
        }
    }

    public static void assertNotBlank(final String str, final Supplier<String> paramNameSupplier) {
        assertNonNull(str, paramNameSupplier);
        if (str.isBlank()) {
            throw new MissingArgumentException(ArgumentErrorCode.MISSING_BLANK, paramNameSupplier.get());
        }
    }

    public static <T> T requireNonNull(final T obj, final String paramName) {
        assertNonNull(obj, paramName);
        return obj;
    }

    public static <T> T requireNonNull(final T obj, final Supplier<String> paramNameSupplier) {
        assertNonNull(obj, paramNameSupplier);
        return obj;
    }

    public static String requireNotBlank(final String str, final String paramName) {
        assertNotBlank(str, paramName);
        return str;
    }

    public static String requireNotBlank(final String str, final Supplier<String> paramNameSupplier) {
        assertNotBlank(str, paramNameSupplier);
        return str;
    }

    public static <T, U, V> void assertAllNotNull(
            final T value1,
            final String name1,
            final U value2,
            final String name2,
            final V value3,
            final String name3) {

        final List<Pair<?>> pairs =
                List.of(new Pair<>(value1, name1), new Pair<>(value2, name2), new Pair<>(value3, name3));

        final String nullFieldNames = pairs.stream()
                .filter(pair -> pair.value() == null)
                .map(Pair::name)
                .collect(Collectors.joining(SPACE));

        if (!nullFieldNames.isEmpty()) {
            throw new MissingArgumentException(ArgumentErrorCode.MISSING_NULL, nullFieldNames);
        }
    }

    private record Pair<T>(T value, String name) {}

    public static void assertTrue(
            final boolean condition, final Supplier<? extends DomainException> exceptionSupplier) {
        if (!condition) {
            throw exceptionSupplier.get();
        }
    }
}
