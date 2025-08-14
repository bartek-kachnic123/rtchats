package com.kachnic.rtchats.modules.user.config;

import com.kachnic.rtchats.modules.user.domain.DefaultUserCredentialService;
import com.kachnic.rtchats.modules.user.domain.PasswordHasher;
import com.kachnic.rtchats.modules.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class UserDomainConfig {

    @Bean
    public DefaultUserCredentialService userCredentialService(
            final UserRepository users, final PasswordHasher passwordHasher) {
        return new DefaultUserCredentialService(users, passwordHasher);
    }
}
