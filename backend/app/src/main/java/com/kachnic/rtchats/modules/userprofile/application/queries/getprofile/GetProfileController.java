package com.kachnic.rtchats.modules.userprofile.application.queries.getprofile;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@RestController
@Validated
@AllArgsConstructor
class GetProfileController {

    private final GetProfileService service;

    @GetMapping("${api.paths.profile}")
    @ResponseStatus(HttpStatus.OK)
    ProfileDto get(@PathVariable @NotBlank final String profileLink) {
        final GetProfileQuery query = new GetProfileQuery(profileLink);
        return service.execute(query);
    }
}
