package com.kachnic.rtchats.modules.userprofile.application.queries.getprofile;

import com.kachnic.rtchats.libs.application.Query;

record GetProfileQuery(String profileLink) implements Query {}
