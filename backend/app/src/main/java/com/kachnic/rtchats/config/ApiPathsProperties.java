package com.kachnic.rtchats.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;

@ConfigurationProperties(prefix = "api.paths")
@Getter
@AllArgsConstructor
final class ApiPathsProperties {

    private final String users;
    private final String login;
    private final String logout;
}
