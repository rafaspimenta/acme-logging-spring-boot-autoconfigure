package com.pimenta.acmeloggingspringbootautoconfigure.config;

import com.pimenta.acmelogging.LogLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "acme.logging")
@Getter
@Setter
public class AcmeLoggingProperties {
    private boolean enabled = true;
    private LogLevel level = LogLevel.INFO;
}
