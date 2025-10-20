package com.kachnic.rtchats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
class WebSecurityConfig {

    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity http, final ApiPathsProperties paths) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.requestMatchers(
                        "/api-docs", "/api-docs/swagger-ui/*", "/api-docs/swagger-config", "/error")
                .permitAll()
                .requestMatchers(HttpMethod.POST, paths.getUsers())
                .permitAll()
                .anyRequest()
                .authenticated());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
