package com.pimenta.acmeloggingspringbootautoconfigure.config;

import com.pimenta.acmelogging.AcmeLogger;
import com.pimenta.acmelogging.LogLevel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class AcmeLoggingAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(AcmeLoggingAutoConfiguration.class));

    @Test
    void shouldAutoConfigureAcmeLoggerWithDefaultProperties() {
        contextRunner
                .run(context -> {
                    assertThat(context).hasSingleBean(AcmeLogger.class);
                    AcmeLogger logger = context.getBean(AcmeLogger.class);
                    assertThat(logger.getLogLevel()).isEqualTo(LogLevel.INFO);
                });
    }

    @Test
    void shouldConfigureAcmeLoggerWithCustomLogLevel() {
        contextRunner
                .withPropertyValues("acme.logging.level=DEBUG")
                .run(context -> {
                    assertThat(context).hasSingleBean(AcmeLogger.class);
                    AcmeLogger logger = context.getBean(AcmeLogger.class);
                    assertThat(logger.getLogLevel()).isEqualTo(LogLevel.DEBUG);
                });
    }

    @Test
    void shouldConfigureAcmeLoggerWithWarnLevel() {
        contextRunner
                .withPropertyValues("acme.logging.level=WARN")
                .run(context -> {
                    assertThat(context).hasSingleBean(AcmeLogger.class);
                    AcmeLogger logger = context.getBean(AcmeLogger.class);
                    assertThat(logger.getLogLevel()).isEqualTo(LogLevel.WARN);
                });
    }

    @Test
    void shouldConfigureAcmeLoggerWithErrorLevel() {
        contextRunner
                .withPropertyValues("acme.logging.level=ERROR")
                .run(context -> {
                    assertThat(context).hasSingleBean(AcmeLogger.class);
                    AcmeLogger logger = context.getBean(AcmeLogger.class);
                    assertThat(logger.getLogLevel()).isEqualTo(LogLevel.ERROR);
                });
    }

    @Test
    void shouldNotCreateAcmeLoggerWhenAlreadyDefined() {
        contextRunner
                .withUserConfiguration(UserDefinedAcmeLoggerConfiguration.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(AcmeLogger.class);
                    AcmeLogger logger = context.getBean(AcmeLogger.class);
                    // Should use the user-defined logger, not the autoconfigured one
                    assertThat(logger.getLogLevel()).isEqualTo(LogLevel.DEBUG);
                });
    }

    @Test
    void shouldCreateAcmeLoggerPropertiesBean() {
        contextRunner
                .run(context -> {
                    assertThat(context).hasSingleBean(AcmeLoggingProperties.class);
                    AcmeLoggingProperties properties = context.getBean(AcmeLoggingProperties.class);
                    assertThat(properties.getLevel()).isEqualTo(LogLevel.INFO);
                    assertThat(properties.isEnabled()).isTrue();
                });
    }

    @Test
    void shouldBindPropertiesCorrectly() {
        contextRunner
                .withUserConfiguration(PropertiesConfiguration.class)
                .withPropertyValues(
                        "acme.logging.level=WARN",
                        "acme.logging.enabled=false")
                .run(context -> {
                    AcmeLoggingProperties properties = context.getBean(AcmeLoggingProperties.class);
                    assertThat(properties.getLevel()).isEqualTo(LogLevel.WARN);
                    assertThat(properties.isEnabled()).isFalse();
                });
    }

    @Test
    void shouldNotCreateAcmeLoggerWhenEnabledIsFalse() {
        contextRunner
                .withPropertyValues("acme.logging.enabled=false")
                .run(context -> {
                    // When enabled=false, the entire auto-configuration is disabled
                    // so AcmeLogger bean should not be created
                    assertThat(context).doesNotHaveBean(AcmeLogger.class);
                    // AcmeLoggingProperties bean is also not created because
                    // @EnableConfigurationProperties is part of the disabled auto-configuration
                    assertThat(context).doesNotHaveBean(AcmeLoggingProperties.class);
                });
    }

    @Configuration
    @EnableConfigurationProperties(AcmeLoggingProperties.class)
    static class PropertiesConfiguration {
    }

    @Configuration
    static class UserDefinedAcmeLoggerConfiguration {
        @Bean
        public AcmeLogger acmeLogger() {
            AcmeLogger logger = new AcmeLogger();
            logger.setLogLevel(LogLevel.DEBUG);
            return logger;
        }
    }
}
