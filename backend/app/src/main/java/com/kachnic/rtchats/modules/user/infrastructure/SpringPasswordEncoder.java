package com.kachnic.rtchats.modules.user.infrastructure;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kachnic.rtchats.modules.user.domain.service.PasswordHasher;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class SpringPasswordEncoder implements PasswordHasher {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String hash(final String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
