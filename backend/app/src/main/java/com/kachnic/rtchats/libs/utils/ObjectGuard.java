package com.kachnic.rtchats.libs.utils;

import java.util.function.Supplier;

import com.kachnic.rtchats.libs.exceptions.MissingArgumentException;

public final class ObjectGuard {

    private ObjectGuard() {}

    public static <T> T requireNotNull(T candidate, String paramName) {
        assertNotNull(candidate, paramName);
        return candidate;
    }

    public static <T> void assertNotNull(T candidate, String paramName) {
        if (candidate == null) {
            throwNullArgument(paramName);
        }
    }

    public static <T> T requireNotNull(T candidate, Supplier<String> paramNameSupplier) {
        assertNotNull(candidate, paramNameSupplier);
        return candidate;
    }

    public static <T> void assertNotNull(T candidate, Supplier<String> paramNameSupplier) {
        if (candidate == null) {
            throwNullArgument(paramNameSupplier.get());
        }
    }

    private static void throwNullArgument(String paramName) {
        throw new MissingArgumentException("Argument '" + paramName + "' is null");
    }
}
