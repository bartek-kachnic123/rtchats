package com.kachnic.rtchats.libs.exceptions;

import java.util.List;

public interface LocalizableMessage {
    String getCode();

    List<Object> getArgs();
}
