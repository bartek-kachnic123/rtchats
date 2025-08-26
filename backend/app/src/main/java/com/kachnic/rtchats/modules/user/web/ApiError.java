package com.kachnic.rtchats.modules.user.web;

record ApiError(String key, String message) {
    private static final String DEFAULT_KEY = "error";

    /* package */ static ApiError of(final String message) {
        return new ApiError(DEFAULT_KEY, message);
    }

    /* package */ static ApiError of(final String key, final String message) {
        return new ApiError(key, message);
    }
}
