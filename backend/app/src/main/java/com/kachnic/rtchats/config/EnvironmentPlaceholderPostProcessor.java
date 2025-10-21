package com.kachnic.rtchats.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.util.PlaceholderResolutionException;

@Order
class EnvironmentPlaceholderPostProcessor implements EnvironmentPostProcessor {

    private static final String UNRESOLVED_PREFIX = "Unresolved placeholders found in properties:";

    @Override
    public void postProcessEnvironment(final ConfigurableEnvironment environment, SpringApplication application) {
        final Set<String> propertyNames = getAllPropertyNames(environment.getPropertySources());
        validateEnvironmentPlaceholders(environment, propertyNames);
    }

    private Set<String> getAllPropertyNames(final PropertySources sources) {
        return sources.stream()
                .filter(source -> source instanceof EnumerablePropertySource<?>)
                .map(source -> ((EnumerablePropertySource<?>) source).getPropertyNames())
                .flatMap(Arrays::stream)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private void validateEnvironmentPlaceholders(
            final ConfigurableEnvironment environment, final Set<String> propertyNames) {
        final List<String> errorMessages = new ArrayList<>();
        for (final String propertyName : propertyNames) {
            try {
                environment.getProperty(propertyName);
            } catch (PlaceholderResolutionException exception) {
                errorMessages.add(String.format("\t%s: %s%n", propertyName, exception.getMessage()));
            }
        }
        failIfAny(errorMessages);
    }

    private void failIfAny(final List<String> errorMessages) {
        if (!errorMessages.isEmpty()) {
            throw new IllegalStateException(
                    UNRESOLVED_PREFIX + System.lineSeparator() + String.join(System.lineSeparator(), errorMessages));
        }
    }
}
