package com.alpha.galiaf;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GaliafApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaliafApplication.class, args);
		Sentry.init(options -> {
			options.setDsn("https://a3730c176137847d3dea63b5b0fa9447@o4509091708928000.ingest.us.sentry.io/4509091710042112");
			options.setRelease("my-spring-app@1.0.2");
			options.addInAppInclude("com.alpha.galiaf");
		});
		Sentry.captureMessage("✅ Test log from Spring Boot main method (integration check)");
	}

}
