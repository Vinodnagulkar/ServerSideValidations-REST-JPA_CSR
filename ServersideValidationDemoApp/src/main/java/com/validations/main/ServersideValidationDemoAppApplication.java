package com.validations.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * @author vinod.nagulkar
 */

/**
 * This is main class which starts the application.
 * @EnableJpaRepositories is used for Spring Data which enables the transactions
 * @EntityScan Configures the base packages used by auto-configuration when scanning for entity classes
 * @SpringBootApplication annotation is used to mark a configuration class that declares one or more @Bean methods and also triggers auto-configuration and component scanning
 */
@SpringBootApplication(scanBasePackages = {"com.validations.web","com.validations.*"})
@EntityScan({"com.validations.*"})
@EnableJpaRepositories("com.validations.repository")
public class ServersideValidationDemoAppApplication {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ServersideValidationDemoAppApplication.class);
	
	public static void main(String[] args) {
		
		SpringApplication.run(ServersideValidationDemoAppApplication.class, args);
		LOGGER.info("\n\n*******Your application is started!********");
	}

}
