package com.kachnic.rtchats.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.util.PlaceholderResolutionException;

@Order
public class EnvironmentPlaceholderPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(final ConfigurableEnvironment environment, SpringApplication application) {
        final List<String> propertyNames = getAllPropertyNames(environment.getPropertySources());
        validateEnvPlaceholders(environment, propertyNames);
    }

    private List<String> getAllPropertyNames(final MutablePropertySources propertySources) {
        final List<String> propertyNames = new ArrayList<>();

        for (final PropertySource<?> propertySource : propertySources) {
            if (propertySource instanceof EnumerablePropertySource<?> eps) {

                propertyNames.addAll(Arrays.asList(eps.getPropertyNames()));
            }
        }

        return propertyNames;
    }

    private void validateEnvPlaceholders(final ConfigurableEnvironment environment, final List<String> propertyNames) {
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
            throw new IllegalStateException("Unresolved placeholders found in properties:\n" + errorMessages);
        }
    }
}
