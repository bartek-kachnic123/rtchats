package com.kachnic.rtchats.libs.spring;

import java.util.Locale;
import java.util.Optional;

import com.kachnic.rtchats.libs.exceptions.LocalizableMessage;

public interface MessageResolver {
    Optional<String> resolve(LocalizableMessage message, Locale locale);

    String resolveOrDefault(LocalizableMessage message, Locale locale);

    String defaultMessage(Locale locale);
}
