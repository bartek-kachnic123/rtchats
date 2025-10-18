package com.kachnic.rtchats.libs.spring;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kachnic.rtchats.libs.exceptions.LocalizableMessage;

abstract class AbstractMessageResolver implements MessageResolver {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String resolveOrDefault(final LocalizableMessage message, final Locale locale) {
        return resolve(message, locale).orElseGet(() -> {
            log.atWarn().addArgument(message::getCode).log("Using global default message for message code: {}");
            return defaultMessage(locale);
        });
    }
}
