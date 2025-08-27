package com.kachnic.rtchats.modules.user.domain;

import com.kachnic.rtchats.libs.specs.Specification;
import com.kachnic.rtchats.modules.user.domain.exceptions.EmailAlreadyTakenException;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Password;

public class DefaultUserCredentialService implements UserCredentialService {

    private final UserRepository users;
    private final PasswordHasher passwordHasher;

    public DefaultUserCredentialService(final UserRepository users, final PasswordHasher passwordHasher) {
        this.users = users;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public Email createNewEmail(final String value) {
        final Email email = Email.of(value);

        if (users.existsByEmail(email)) {
            throw new EmailAlreadyTakenException(value);
        }

        return email;
    }

    @Override
    public Password createNewPassword(final String value) {
        return createNewPassword(value, StrongPasswordSpecification.getDefault());
    }

    @Override
    public Password createNewPassword(final String value, final Specification<String> passwordSpec) {
        passwordSpec.check(value, Password.class.getSimpleName());

        final String hashedValue = passwordHasher.hash(value);

        return Password.of(hashedValue);
    }
}
