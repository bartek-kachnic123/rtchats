package com.kachnic.rtchats.modules.user.web;

record CreateUserRequest(String email, String username, String password, String confirmPassword) {}
