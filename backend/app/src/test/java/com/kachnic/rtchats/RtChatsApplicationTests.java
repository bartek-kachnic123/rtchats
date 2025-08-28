package com.kachnic.rtchats;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.kachnic.rtchats.config.TestcontainersConfiguration;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class RtChatsApplicationTests {

    @Test
    @SuppressWarnings("PMD.UnitTestShouldIncludeAssert")
    void contextLoads() {
        /*
         * Verifies that the Spring application context loads without errors.
         * If the context fails to initialize, this test will fail automatically.
         */
    }
}
