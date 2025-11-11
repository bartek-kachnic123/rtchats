package com.kachnic.rtchats.modules.userprofile.domain.valueobjects;

public record Avatar(String value) {
    public static Avatar of(final String value) {
        return new Avatar(value);
    }

    public static Avatar empty() {
        return new Avatar(null);
    }
}
