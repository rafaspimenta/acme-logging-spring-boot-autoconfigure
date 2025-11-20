package com.pimenta.acmeloggingspringbootautoconfigure.config;

import com.pimenta.acmelogging.AcmeLogger;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(AcmeLoggingProperties.class)
@ConditionalOnClass(AcmeLogger.class)
@ConditionalOnProperty(prefix = "acme.logging", name = "enabled", havingValue = "true", matchIfMissing = true)
public class AcmeLoggingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AcmeLogger acmeLogger(AcmeLoggingProperties properties) {
        AcmeLogger acmeLogger = new AcmeLogger();
        acmeLogger.setLogLevel(properties.getLevel());
        return acmeLogger;
    }
}
