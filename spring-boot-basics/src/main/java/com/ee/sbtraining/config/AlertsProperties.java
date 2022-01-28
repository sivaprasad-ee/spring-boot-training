package com.ee.sbtraining.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "alerts")
@Setter
@Getter
@ToString
public class AlertsProperties {
    private String email;
    private String phone;
}
