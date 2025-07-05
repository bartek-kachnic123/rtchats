package com.kachnic.rtchats.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.util.PlaceholderResolutionException;

@Order(Ordered.LOWEST_PRECEDENCE)
public class EnvironmentPlaceholderPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(
            ConfigurableEnvironment environment, SpringApplication application) {

        List<String> propertyNames = getAllPropertyNames(environment);
        validateEnvPlaceholders(environment, propertyNames);
    }

    private List<String> getAllPropertyNames(ConfigurableEnvironment environment) {
        List<String> propertyNames = new ArrayList<>();

        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            if (propertySource instanceof EnumerablePropertySource<?> eps) {

                propertyNames.addAll(Arrays.asList(eps.getPropertyNames()));
            }
        }

        return propertyNames;
    }

    private void validateEnvPlaceholders(
            ConfigurableEnvironment environment, List<String> propertyNames) {
        StringBuilder missingPlaceholderMessages = new StringBuilder();
        String message;
        for (String propName : propertyNames) {
            try {
                environment.getProperty(propName);

            } catch (PlaceholderResolutionException exc) {
                message = String.format("\t%s: %s%n", propName, exc.getMessage());
                missingPlaceholderMessages.append(message);
            }
        }

        if (missingPlaceholderMessages.length() > 0) {
            throw new IllegalStateException(
                    "Unresolved placeholders found in properties:\n"
                            + missingPlaceholderMessages.toString());
        }
    }
}
