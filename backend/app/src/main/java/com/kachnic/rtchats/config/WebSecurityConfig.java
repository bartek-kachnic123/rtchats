package com.kachnic.rtchats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

@Configuration(proxyBeanMethods = false)
class WebSecurityConfig {

    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity http, final ApiPathsProperties apiPaths)
            throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                "/api-docs", "/api-docs/swagger-ui/*", "/api-docs/swagger-config", "/error")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, apiPaths.getUsers())
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(handler -> handler.authenticationEntryPoint(new RestAuthenticationEntryPoint()))
                .formLogin(login -> login.usernameParameter("email")
                        .loginProcessingUrl(apiPaths.getLogin())
                        .successHandler(
                                (request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
                        .failureHandler((request, response, authentication) ->
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)))
                .logout(logout -> logout.logoutUrl(apiPaths.getLogout())
                        .logoutSuccessHandler(
                                (request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK)));
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
