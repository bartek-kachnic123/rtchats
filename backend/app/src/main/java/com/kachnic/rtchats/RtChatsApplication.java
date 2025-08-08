package com.kachnic.rtchats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
        basePackages = {
            "com.kachnic.rtchats.modules.user.infrastructure",
        })
public class RtChatsApplication {

    public static void main(final String[] args) {
        SpringApplication.run(RtChatsApplication.class, args);
    }
}
