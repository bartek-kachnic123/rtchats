package com.kachnic.rtchats.modules.user.application.commands.createuser;

import com.kachnic.rtchats.libs.application.Command;

public record CreateUserCommand(String email, String username, String password) implements Command {}
