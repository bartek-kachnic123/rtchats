package com.kachnic.rtchats.modules.user.domain;

import com.kachnic.rtchats.libs.specs.Specification;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Email;
import com.kachnic.rtchats.modules.user.domain.model.valueobjects.Password;

public interface UserCredentialService {
    Email createNewEmail(String value);

    Password createNewPassword(String value, Specification<String> passwordSpec);
}
