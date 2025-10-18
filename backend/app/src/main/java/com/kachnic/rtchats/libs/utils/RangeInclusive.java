package com.kachnic.rtchats.libs.utils;

public record RangeInclusive(int min, int max) implements Range {
    @Override
    public boolean contains(final int length) {
        return length >= min && length <= max;
    }
}
