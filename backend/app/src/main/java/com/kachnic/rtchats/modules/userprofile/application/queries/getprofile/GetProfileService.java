package com.kachnic.rtchats.modules.userprofile.application.queries.getprofile;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.kachnic.rtchats.libs.application.QueryHandler;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class GetProfileService implements QueryHandler<ProfileDto, GetProfileQuery> {

    private final JdbcTemplate jdbcTemplate;

    private static final String SQL = "SELECT display_name, avatar FROM user_profiles WHERE profile_link = ?";

    @Override
    public ProfileDto execute(final GetProfileQuery query) {
        return jdbcTemplate.queryForObject(
                SQL,
                (result, row) -> new ProfileDto(result.getString("display_name"), result.getString("avatar")),
                query.profileLink());
    }
}
