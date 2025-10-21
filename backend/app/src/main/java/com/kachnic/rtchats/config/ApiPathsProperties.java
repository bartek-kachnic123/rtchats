package com.kachnic.rtchats.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "api.paths")
@Getter
@Setter
class ApiPathsProperties {

    private String users;
}
