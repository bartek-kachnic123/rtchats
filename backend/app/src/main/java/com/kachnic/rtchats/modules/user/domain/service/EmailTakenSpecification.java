package com.kachnic.rtchats.modules.user.domain.service;

import com.kachnic.rtchats.libs.ddd.Specification;
import com.kachnic.rtchats.modules.user.domain.model.UserRepository;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;

public class EmailTakenSpecification implements Specification<Email> {

    private final UserRepository users;

    public EmailTakenSpecification(final UserRepository users) {
        this.users = users;
    }

    @Override
    public boolean isSatisfiedBy(final Email candidate) {
        return users.existsByEmail(candidate);
    }
}
