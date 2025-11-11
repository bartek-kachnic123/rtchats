package com.kachnic.rtchats.modules.userprofile.application.queries.getauthenticatedprofile;

import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kachnic.rtchats.libs.spring.security.AppUserDetails;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
class GetAuthenticatedProfileController {

    private final GetAuthenticatedProfileService service;

    @GetMapping("${api.paths.my-profile}")
    AuthenticatedProfileDto get(@AuthenticationPrincipal final AppUserDetails userDetails) {
        final UUID userId = userDetails.getUserId();
        final GetAuthenticatedProfileQuery query = new GetAuthenticatedProfileQuery(userId);
        return service.execute(query);
    }
}
