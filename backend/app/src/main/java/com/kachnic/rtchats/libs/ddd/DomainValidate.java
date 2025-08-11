package com.kachnic.rtchats.libs.ddd;

import com.kachnic.rtchats.libs.exceptions.DomainException;
import com.kachnic.rtchats.libs.exceptions.MissingArgumentException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class DomainValidate {

    private static final String NULL_MSG_SUFFIX = " must not be null";
    private static final String BLANK_MSG_SUFFIX = " must not be blank";

    private DomainValidate() {}

    public static <T> void assertNonNull(final T obj, final String paramName) {
        if (obj == null) {
            throw new MissingArgumentException(paramName + NULL_MSG_SUFFIX);
        }
    }

    public static void assertNonNull(final Object obj, final Supplier<String> paramNameSupplier) {
        if (obj == null) {
            throw new MissingArgumentException(paramNameSupplier.get() + NULL_MSG_SUFFIX);
        }
    }

    public static void assertNotBlank(final String str, final String paramName) {
        assertNonNull(str, paramName);
        if (str.isBlank()) {
            throw new MissingArgumentException(paramName + BLANK_MSG_SUFFIX);
        }
    }

    public static void assertNotBlank(final String str, final Supplier<String> paramNameSupplier) {
        assertNonNull(str, paramNameSupplier);
        if (str.isBlank()) {
            throw new MissingArgumentException(paramNameSupplier.get() + BLANK_MSG_SUFFIX);
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

        final String errorMessage = pairs.stream()
                .filter(pair -> pair.value() == null)
                .map(pair -> pair.name() + NULL_MSG_SUFFIX)
                .collect(Collectors.joining(System.lineSeparator()));

        if (!errorMessage.isEmpty()) {
            throw new MissingArgumentException(errorMessage);
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
