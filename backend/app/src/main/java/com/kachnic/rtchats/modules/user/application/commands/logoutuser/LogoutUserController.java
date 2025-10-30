package com.kachnic.rtchats.modules.user.application.commands.logoutuser;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("${api.paths.logout}")
public class LogoutUserController {

    final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    void logout(
            final Authentication authentication, final HttpServletRequest request, final HttpServletResponse response) {
        logoutHandler.logout(request, response, authentication);
    }
}
