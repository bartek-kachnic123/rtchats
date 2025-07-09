package com.kachnic.rtchats;

import com.kachnic.rtchats.config.TestcontainersConfiguration;
import org.springframework.boot.SpringApplication;

public class TestRtChatsApplication {

    public static void main(final String[] args) {
        SpringApplication.from(RtChatsApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
