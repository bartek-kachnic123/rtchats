package com.kachnic.rtchats.modules.user.domain.service;

import com.kachnic.rtchats.libs.ddd.Specification;
import com.kachnic.rtchats.modules.user.domain.exceptions.EmailAlreadyTakenException;
import com.kachnic.rtchats.modules.user.domain.model.UserRepository;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Password;

public class DefaultUserCredentialService implements UserCredentialService {

    private final PasswordHasher passwordHasher;
    private final Specification<Email> emailTakenSpec;

    public DefaultUserCredentialService(final UserRepository users, final PasswordHasher passwordHasher) {
        this(passwordHasher, new EmailTakenSpecification(users));
    }

    public DefaultUserCredentialService(
            final PasswordHasher passwordHasher, final Specification<Email> emailTakenSpec) {
        this.passwordHasher = passwordHasher;
        this.emailTakenSpec = emailTakenSpec;
    }

    @Override
    public Email createNewEmail(final String value) {
        final Email email = Email.of(value);
        if (emailTakenSpec.isSatisfiedBy(email)) {
            throw new EmailAlreadyTakenException(value);
        }
        return email;
    }

    @Override
    public Password createNewPassword(final String value) {
        Password.requireStrong(value);
        final String hashedValue = passwordHasher.hash(value);
        return Password.of(hashedValue);
    }
}
