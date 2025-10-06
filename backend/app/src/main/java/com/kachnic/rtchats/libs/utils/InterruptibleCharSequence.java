package com.kachnic.rtchats.libs.utils;

import java.util.concurrent.CancellationException;

record InterruptibleCharSequence(CharSequence inner) implements CharSequence {
    private static final String TIMEOUT_MESSAGE =
            "Regex evaluation was interrupted due to task timeout or cancellation";

    @Override
    public int length() {
        return inner.length();
    }

    @Override
    public char charAt(final int index) {
        if (Thread.currentThread().isInterrupted()) {
            throw new CancellationException(TIMEOUT_MESSAGE);
        }

        return inner.charAt(index);
    }

    @Override
    public CharSequence subSequence(final int start, final int end) {
        return inner.subSequence(start, end);
    }
}
