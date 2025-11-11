package com.kachnic.rtchats.modules.userprofile.application.queries.getauthenticatedprofile;

import java.util.UUID;

import com.kachnic.rtchats.libs.application.Query;

record GetAuthenticatedProfileQuery(UUID owner_id) implements Query {}
