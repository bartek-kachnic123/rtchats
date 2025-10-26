package com.kachnic.rtchats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration(proxyBeanMethods = false)
class OpenApiSecurityConfig {

    private static final String AUTH_SCHEMA = "cookieAuth";
    private static final String AUTH_COOKIE = "JSESSIONID";

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .schemaRequirement(
                        AUTH_SCHEMA,
                        new SecurityScheme()
                                .name(AUTH_COOKIE)
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.COOKIE))
                .addSecurityItem(new SecurityRequirement().addList(AUTH_SCHEMA));
    }
}
