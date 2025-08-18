package com.kachnic.rtchats.libs.spring;

import com.kachnic.rtchats.libs.exceptions.LocalizableMessage;
import java.util.Locale;
import java.util.Optional;
import org.springframework.validation.ObjectError;

public interface MessageResolver {
    Optional<String> resolve(LocalizableMessage message, Locale locale);

    Optional<String> resolve(ObjectError error, Locale locale);

    String resolveOrDefault(ObjectError err, Locale locale);

    String resolveOrDefault(LocalizableMessage message, Locale locale);

    String defaultMessage(Locale locale);
}
