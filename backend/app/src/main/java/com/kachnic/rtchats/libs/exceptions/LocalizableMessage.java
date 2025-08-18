package com.kachnic.rtchats.libs.exceptions;

public interface LocalizableMessage {
    String getCode();

    Object[] getArgs();
}
