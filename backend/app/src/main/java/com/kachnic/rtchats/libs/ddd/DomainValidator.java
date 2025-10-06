package com.kachnic.rtchats.libs.ddd;

// public final class DomainValidator {

//
//    private static final String PARAM_SEPARATOR = " ";
//
//    private DomainValidator() {}
//
//    public static <T> void assertNonNull(final T obj, final String paramName) {
//        if (obj == null) {
//            throw new MissingArgumentException(ArgumentErrorCode.MISSING_NULL, paramName);
//        }
//    }
//
//    public static <T> void assertNonNull(final T obj, final Supplier<String> paramNameSupplier) {
//        assertNonNull(obj, paramNameSupplier.get());
//    }
//
//    public static <T> T requireNonNull(final T obj, final String paramName) {
//        assertNonNull(obj, paramName);
//        return obj;
//    }
//
//    public static <T> T requireNonNull(final T obj, final Supplier<String> paramNameSupplier) {
//        return requireNonNull(obj, paramNameSupplier.get());
//    }
//
//    public static ArgsBuilder argsBuilder() {
//        return new ArgsBuilder();
//    }
//
//    public static final class ArgsBuilder {

//
//        private final Map<String, Object> paramsData = new LinkedHashMap<>();
//
//        public ArgsBuilder add(final Object value, final String name) {
//            paramsData.put(name, value);
//            return this;
//        }
//    }
//
//    public static void assertAllNotNull(ArgsBuilder builder) {
//        final Map<String, Object> paramsData = builder.paramsData;
//        final List<String> nullFieldNames = findNullFieldNames(paramsData);
//        throwIfAny(nullFieldNames);
//    }
//
//    public static List<String> findNullFieldNames(final Map<String, ?> paramsData) {
//        return paramsData.entrySet().stream()
//                .filter(entry -> entry.getValue() == null)
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());
//    }
//
//    private static void throwIfAny(final List<String> nullFieldNames) {
//        if (!nullFieldNames.isEmpty()) {
//            throw new MissingArgumentException(
//                    ArgumentErrorCode.MISSING_NULL, String.join(PARAM_SEPARATOR, nullFieldNames));
//        }
//    }
//
//    public static void assertTrue(
//            final boolean condition, final Supplier<? extends DomainException> exceptionSupplier) {
//        if (!condition) {
//            throw exceptionSupplier.get();
//        }
//    }
// }
