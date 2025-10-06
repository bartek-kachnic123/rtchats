package com.kachnic.rtchats.modules.user.domain.service;

@FunctionalInterface
public interface PasswordHasher {
    String hash(String rawPassword);
}
