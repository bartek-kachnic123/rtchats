package com.kachnic.rtchats.libs.ddd.specs;

@FunctionalInterface
public interface Specification<T> {
    void check(T candidate, String paramName);

    default Specification<T> and(final Specification<T> other) {
        return (candidate, paramName) -> {
            check(candidate, paramName);
            other.check(candidate, paramName);
        };
    }
}
