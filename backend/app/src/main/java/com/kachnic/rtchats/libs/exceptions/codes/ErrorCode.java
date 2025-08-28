package com.kachnic.rtchats.libs.exceptions.codes;

import java.io.Serializable;
import java.util.Locale;

@FunctionalInterface
public interface ErrorCode extends Serializable {
    char ENUM_SEPARATOR = '_';
    char CODE_SEPARATOR = '-';

    String getValue();

    static String format(final String prefix, final String name) {
        return prefix + name.toLowerCase(Locale.ROOT).replace(ENUM_SEPARATOR, CODE_SEPARATOR);
    }
}
