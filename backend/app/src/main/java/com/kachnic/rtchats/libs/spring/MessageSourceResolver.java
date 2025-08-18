package com.kachnic.rtchats.libs.spring;

import com.kachnic.rtchats.libs.exceptions.LocalizableMessage;
import java.util.Locale;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

@Component
@AllArgsConstructor
public final class MessageSourceResolver extends AbstractMessageResolver {
    private static final String NO_SUCH_MSG_CODE = "global.errors.no-such-message";
    private static final String FALLBACK_MESSAGE = "Global Message is missing";

    private final MessageSource messageSource;

    @Override
    public Optional<String> resolve(final LocalizableMessage message, final Locale locale) {

        return Optional.ofNullable(messageSource.getMessage(message.getCode(), message.getArgs(), null, locale));
    }

    @Override
    public Optional<String> resolve(final ObjectError error, final Locale locale) {
        final String message = messageSource.getMessage(error, locale);
        return isPlaceholder(message) ? Optional.empty() : Optional.of(message);
    }

    private boolean isPlaceholder(final String message) {
        return message.startsWith("{") && message.endsWith("}");
    }

    @Override
    public String defaultMessage(final Locale locale) {
        return messageSource.getMessage(NO_SUCH_MSG_CODE, null, FALLBACK_MESSAGE, locale);
    }
}
