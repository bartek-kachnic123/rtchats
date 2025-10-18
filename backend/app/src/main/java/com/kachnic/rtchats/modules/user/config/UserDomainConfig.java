package com.kachnic.rtchats.modules.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kachnic.rtchats.modules.user.domain.model.UserRepository;
import com.kachnic.rtchats.modules.user.domain.service.DefaultPasswordPolicy;
import com.kachnic.rtchats.modules.user.domain.service.DefaultUserCredentialService;
import com.kachnic.rtchats.modules.user.domain.service.PasswordHasher;
import com.kachnic.rtchats.modules.user.domain.service.PasswordPolicy;

@Configuration(proxyBeanMethods = false)
class UserDomainConfig {

    @Bean
    public DefaultUserCredentialService userCredentialService(
            final UserRepository users, final PasswordPolicy passwordPolicy, final PasswordHasher passwordHasher) {
        return new DefaultUserCredentialService(users, passwordPolicy, passwordHasher);
    }

    @Bean
    public DefaultPasswordPolicy passwordPolicy() {
        return new DefaultPasswordPolicy();
    }
}
