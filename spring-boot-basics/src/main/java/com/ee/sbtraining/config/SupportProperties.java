package com.ee.sbtraining.config;

import com.ee.sbtraining.models.Address;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "support")
@Data
public class SupportProperties {
    private String email;
    private String phone;
    private Address address;

}
