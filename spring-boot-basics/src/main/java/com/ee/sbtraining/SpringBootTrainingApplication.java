package com.ee.sbtraining;

import com.ee.sbtraining.config.AlertsProperties;
import com.ee.sbtraining.config.ApplicationProperties;
import com.ee.sbtraining.config.SupportProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SupportProperties.class, AlertsProperties.class, ApplicationProperties.class})
public class SpringBootTrainingApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootTrainingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTrainingApplication.class, args);
		logger.info("this is a INFO message");
		logger.debug("this is a DEBUG message");
	}

}
