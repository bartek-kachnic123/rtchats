package com.kachnic.rtchats.modules.userprofile.application.queries.getauthenticatedprofile;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.kachnic.rtchats.libs.application.QueryHandler;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class GetAuthenticatedProfileService implements QueryHandler<AuthenticatedProfileDto, GetAuthenticatedProfileQuery> {

    private final JdbcTemplate jdbcTemplate;
    private static final String SQL = "SELECT display_name, profile_link FROM user_profiles WHERE owner_id = ?";

    @Override
    public AuthenticatedProfileDto execute(final GetAuthenticatedProfileQuery query) {
        return jdbcTemplate.queryForObject(
                SQL,
                (result, row) ->
                        new AuthenticatedProfileDto(result.getString("display_name"), result.getString("profile_link")),
                query.owner_id());
    }
}
