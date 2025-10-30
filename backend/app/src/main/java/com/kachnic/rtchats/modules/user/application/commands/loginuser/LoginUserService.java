package com.kachnic.rtchats.modules.user.application.commands.loginuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kachnic.rtchats.libs.application.CommandHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
class LoginUserService implements CommandHandler<LoginUserCommand> {

    private final SecurityContextRepository contextRepository;
    private final AuthenticationManager authManager;

    public LoginUserService(
            final AuthenticationManager authManager, final SecurityContextRepository contextRepository) {
        this.authManager = authManager;
        this.contextRepository = contextRepository;
    }

    @Autowired
    public LoginUserService(final AuthenticationManager authManager) {
        this(authManager, new HttpSessionSecurityContextRepository());
    }

    @Override
    public void handle(final LoginUserCommand command) {
        final UsernamePasswordAuthenticationToken token =
                UsernamePasswordAuthenticationToken.unauthenticated(command.email(), command.password());
        final RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes servletAttributes) {
            final HttpServletRequest httpRequest = servletAttributes.getRequest();
            final HttpServletResponse httpResponse = servletAttributes.getResponse();
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
            final Authentication authentication = authManager.authenticate(token);
            storeAuthenticationInSession(authentication, httpRequest, httpResponse);
        }
    }

    private void storeAuthenticationInSession(
            final Authentication authentication,
            final HttpServletRequest httpRequest,
            final HttpServletResponse httpResponse) {
        final SecurityContextHolderStrategy contextHolder = SecurityContextHolder.getContextHolderStrategy();
        final SecurityContext context = contextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        contextHolder.setContext(context);
        contextRepository.saveContext(context, httpRequest, httpResponse);
    }
}
