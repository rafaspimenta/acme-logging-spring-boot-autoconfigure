package com.pimenta.acmeloggingspringbootautoconfigure.config;

import com.pimenta.acmelogging.LogLevel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class AcmeLoggingPropertiesTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(TestConfiguration.class);

    @Test
    void shouldHaveDefaultValues() {
        contextRunner
                .run(context -> {
                    AcmeLoggingProperties properties = context.getBean(AcmeLoggingProperties.class);
                    assertThat(properties.getLevel()).isEqualTo(LogLevel.INFO);
                    assertThat(properties.isEnabled()).isTrue();
                });
    }

    @Test
    void shouldBindLevelProperty() {
        contextRunner
                .withPropertyValues("acme.logging.level=DEBUG")
                .run(context -> {
                    AcmeLoggingProperties properties = context.getBean(AcmeLoggingProperties.class);
                    assertThat(properties.getLevel()).isEqualTo(LogLevel.DEBUG);
                });
    }

    @Test
    void shouldBindEnabledProperty() {
        contextRunner
                .withPropertyValues("acme.logging.enabled=false")
                .run(context -> {
                    AcmeLoggingProperties properties = context.getBean(AcmeLoggingProperties.class);
                    assertThat(properties.isEnabled()).isFalse();
                });
    }

    @Test
    void shouldBindAllProperties() {
        contextRunner
                .withPropertyValues(
                        "acme.logging.level=WARN",
                        "acme.logging.enabled=false"
                )
                .run(context -> {
                    AcmeLoggingProperties properties = context.getBean(AcmeLoggingProperties.class);
                    assertThat(properties.getLevel()).isEqualTo(LogLevel.WARN);
                    assertThat(properties.isEnabled()).isFalse();
                });
    }

    @Test
    void shouldHandleCaseInsensitiveLevel() {
        contextRunner
                .withPropertyValues("acme.logging.level=error")
                .run(context -> {
                    AcmeLoggingProperties properties = context.getBean(AcmeLoggingProperties.class);
                    assertThat(properties.getLevel()).isEqualTo(LogLevel.ERROR);
                });
    }

    @Configuration
    @EnableConfigurationProperties(AcmeLoggingProperties.class)
    static class TestConfiguration {
    }
}

