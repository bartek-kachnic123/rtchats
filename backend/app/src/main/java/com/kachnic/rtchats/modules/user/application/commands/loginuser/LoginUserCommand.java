package com.kachnic.rtchats.modules.user.application.commands.loginuser;

import com.kachnic.rtchats.libs.application.Command;

record LoginUserCommand(String email, String password) implements Command {}
