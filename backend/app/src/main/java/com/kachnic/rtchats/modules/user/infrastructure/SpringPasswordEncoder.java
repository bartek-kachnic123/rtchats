package com.kachnic.rtchats.modules.user.infrastructure;

import com.kachnic.rtchats.modules.user.domain.PasswordHasher;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SpringPasswordEncoder implements PasswordHasher {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String hash(final String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
