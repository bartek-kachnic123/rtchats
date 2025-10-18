package com.kachnic.rtchats.libs.spring;

import java.util.Locale;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.kachnic.rtchats.libs.exceptions.LocalizableMessage;
import com.kachnic.rtchats.libs.exceptions.codes.OperationErrorCode;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public final class MessageSourceResolver extends AbstractMessageResolver {

    private static final String FALLBACK_MESSAGE = "Global Message is missing";

    private final MessageSource messageSource;

    @Override
    public Optional<String> resolve(final LocalizableMessage message, final Locale locale) {
        return Optional.ofNullable(
                messageSource.getMessage(message.getCode(), message.getArgs().toArray(), null, locale));
    }

    @Override
    public String defaultMessage(final Locale locale) {
        return messageSource.getMessage(OperationErrorCode.NO_SUCH_MESSAGE.getValue(), null, FALLBACK_MESSAGE, locale);
    }
}
