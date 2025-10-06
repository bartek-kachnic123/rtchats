package com.kachnic.rtchats.libs.ddd;

@FunctionalInterface
public interface Specification<T> {
    boolean isSatisfiedBy(T candidate);
}
