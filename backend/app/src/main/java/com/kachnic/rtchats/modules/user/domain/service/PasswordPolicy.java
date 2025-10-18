package com.kachnic.rtchats.modules.user.domain.service;

@FunctionalInterface
public interface PasswordPolicy {
    void validate(String password);
}
