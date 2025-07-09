package com.kachnic.rtchats;

import com.kachnic.rtchats.config.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class RtChatsApplicationTests {

    @Test
    @SuppressWarnings("PMD.UnitTestShouldIncludeAssert")
    void contextLoads() {}
}
