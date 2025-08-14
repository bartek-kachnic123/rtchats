package com.kachnic.rtchats.modules.user.domain;

@FunctionalInterface
public interface PasswordHasher {
    String hash(String rawPassword);
}
