package com.kachnic.rtchats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
class WebSecurityConfig {

    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity http, final ApiPathsProperties apiPaths)
            throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                "/api-docs", "/api-docs/swagger-ui/*", "/api-docs/swagger-config", "/error")
                        .permitAll()
                        .requestMatchers(
                                HttpMethod.POST, apiPaths.getUsers(), apiPaths.getLogin(), apiPaths.getLogout())
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(handler -> handler.authenticationEntryPoint(new ApiAuthenticationEntryPoint()));
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(
            final PasswordEncoder passwordEncoder, final UserDetailsService userService) {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(passwordEncoder);
        authProvider.setUserDetailsService(userService);
        return new ProviderManager(authProvider);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
