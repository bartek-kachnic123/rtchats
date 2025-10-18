package com.kachnic.rtchats.config;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.util.PlaceholderResolutionException;

@Order
class EnvironmentPlaceholderPostProcessor implements EnvironmentPostProcessor {

    private static final String UNRESOLVED_PREFIX = "Unresolved placeholders found in properties:\n";

    @Override
    public void postProcessEnvironment(final ConfigurableEnvironment environment, SpringApplication application) {
        final Set<String> propertyNames = getAllPropertyNames(environment.getPropertySources());
        validateEnvPlaceholders(environment, propertyNames);
    }

    private Set<String> getAllPropertyNames(final MutablePropertySources propertySources) {
        final Set<String> propertyNames = new LinkedHashSet<>();
        for (final PropertySource<?> propertySource : propertySources) {
            storePropertyNames(propertySource, propertyNames);
        }
        return propertyNames;
    }

    private void storePropertyNames(final PropertySource<?> propertySource, final Set<String> propertyNames) {
        if (propertySource instanceof EnumerablePropertySource<?> eps) {
            Collections.addAll(propertyNames, eps.getPropertyNames());
        }
    }

    private void validateEnvPlaceholders(final ConfigurableEnvironment environment, final Set<String> propertyNames) {
        final StringBuilder errorMessages = new StringBuilder();
        String message;
        for (final String propName : propertyNames) {
            try {
                environment.getProperty(propName);
            } catch (PlaceholderResolutionException exc) {
                message = String.format("\t%s: %s%n", propName, exc.getMessage());
                errorMessages.append(message);
            }
        }
        if (!errorMessages.isEmpty()) {
            throw new IllegalStateException(UNRESOLVED_PREFIX + errorMessages);
        }
    }
}
