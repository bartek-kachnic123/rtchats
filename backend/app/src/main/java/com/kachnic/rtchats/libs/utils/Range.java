package com.kachnic.rtchats.libs.utils;

public record Range(int min, int max) {
    public Range {
        if (min > max) {
            throw new IllegalArgumentException("Range min cannot be greater than max");
        }
    }

    public boolean containsInclusive(int value) {
        return value >= min && value <= max;
    }

    public static Range of(int min, int max) {
        return new Range(min, max);
    }
}
