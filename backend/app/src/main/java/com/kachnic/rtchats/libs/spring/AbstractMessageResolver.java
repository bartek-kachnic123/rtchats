package com.kachnic.rtchats.libs.spring;

import com.kachnic.rtchats.libs.exceptions.LocalizableMessage;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;

abstract class AbstractMessageResolver implements MessageResolver {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String resolveOrDefault(final LocalizableMessage message, final Locale locale) {
        return resolve(message, locale).orElseGet(() -> {
            log.atWarn().addArgument(message::getCode).log("Using global default message for message code: {}");
            return defaultMessage(locale);
        });
    }

    @Override
    public String resolveOrDefault(final ObjectError err, final Locale locale) {
        final Optional<String> result = resolve(err, locale);

        return result.orElseGet(() -> {
            log.atWarn()
                    .addArgument(() -> Arrays.toString(err.getCodes()))
                    .addArgument(err::getDefaultMessage)
                    .log("Using global default message for ObjectError. Codes: {}, DefaultMessage: {}");
            return defaultMessage(locale);
        });
    }
}
