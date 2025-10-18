package com.kachnic.rtchats.modules.user.domain.service;

import com.kachnic.rtchats.modules.user.domain.exceptions.EmailAlreadyTakenException;
import com.kachnic.rtchats.modules.user.domain.model.UserRepository;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Password;

public class DefaultUserCredentialService implements UserCredentialService {

    private final UserRepository users;
    private final PasswordPolicy passwordPolicy;
    private final PasswordHasher passwordHasher;

    public DefaultUserCredentialService(
            final UserRepository users, final PasswordPolicy passwordPolicy, final PasswordHasher passwordHasher) {
        this.users = users;
        this.passwordPolicy = passwordPolicy;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public Email createNewEmail(final String value) {
        final Email email = Email.of(value);
        if (users.existsBy(email)) {
            throw new EmailAlreadyTakenException("Email is already taken: " + email);
        }
        return email;
    }

    @Override
    public Password createNewPassword(final String value) {
        passwordPolicy.validate(value);
        final String hashedValue = passwordHasher.hash(value);
        return Password.of(hashedValue);
    }
}
