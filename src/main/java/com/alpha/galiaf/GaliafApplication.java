package com.alpha.galiaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GaliafApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaliafApplication.class, args);
//		Sentry.captureMessage("✅ Test log from Spring Boot main method (integration check)");
	}
}
