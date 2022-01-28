package com.ee.sbtraining.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private SupportConfig support = new SupportConfig();
    private AlertsConfig alerts = new AlertsConfig();

    @Setter
    @Getter
    @ToString
    public static class AlertsConfig {
        private String email;
        private String phone;
    }

    @Setter
    @Getter
    @ToString
    public static class SupportConfig {
        private String email;
        private String phone;
    }
}
