package com.kachnic.rtchats.libs.utils;

public interface Range {
    boolean contains(int length);

    int min();

    int max();

    static Range ofInclusive(final int min, final int max) {
        return new RangeInclusive(min, max);
    }
}
