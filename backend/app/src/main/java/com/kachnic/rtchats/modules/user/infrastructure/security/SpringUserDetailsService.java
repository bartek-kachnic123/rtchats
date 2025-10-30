package com.kachnic.rtchats.modules.user.infrastructure.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kachnic.rtchats.modules.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class SpringUserDetailsService implements UserDetailsService {

    final UserRepository users;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return users.findByEmail(username)
                .map(user -> User.builder()
                        .username(user.getEmail().value())
                        .password(user.getPassword().value())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
