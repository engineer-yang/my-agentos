package io.oryxos.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OryxOsApplicationTest {

    @Autowired
    private ConfigurableApplicationContext context;

    @Test
    void startsWithoutExternalServices() {
        assertThat(context.isActive()).isTrue();
    }
}
