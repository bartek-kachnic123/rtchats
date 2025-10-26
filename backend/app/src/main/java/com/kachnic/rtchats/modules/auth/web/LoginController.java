package com.kachnic.rtchats.modules.auth.web;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.paths.login}")
class LoginController {

    private final SecurityContextHolderStrategy contextHolder = SecurityContextHolder.getContextHolderStrategy();
    private final SecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();
    private final AuthenticationManager authManager;

    LoginController(final AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    void login(
            @Valid @RequestBody final LoginRequest loginRequest,
            final HttpServletRequest httpRequest,
            final HttpServletResponse httpResponse) {
        final UsernamePasswordAuthenticationToken token =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email(), loginRequest.password());
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
        final Authentication authentication = authManager.authenticate(token);
        final SecurityContext context = contextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        contextHolder.setContext(context);
        contextRepository.saveContext(context, httpRequest, httpResponse);
    }
}
