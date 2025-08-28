package com.kachnic.rtchats;

import org.springframework.boot.SpringApplication;

import com.kachnic.rtchats.config.TestcontainersConfiguration;

public class TestRtChatsApplication {

    public static void main(final String[] args) {
        SpringApplication.from(RtChatsApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
