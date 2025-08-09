package com.kachnic.rtchats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
class WebSecurityConfig {

    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    @Bean
    /* package */ SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.requestMatchers("/api-docs/**")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/users")
                .permitAll()
                .anyRequest()
                .authenticated());

        return http.build();
    }

    @Bean
    /* package */ PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
