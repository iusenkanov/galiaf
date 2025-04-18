package com.alpha.galiaf;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GaliafApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaliafApplication.class, args);
		Sentry.init(options -> {
			System.out.println("Release set to: " + options.getRelease());
		});
		Sentry.captureMessage("✅ Test log from Spring Boot main method (integration check)");
	}

}
