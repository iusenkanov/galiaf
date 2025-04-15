package com.alpha.galiaf;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GaliafApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaliafApplication.class, args);
		Sentry.init(options -> {
			options.setDsn("https://ea8c8ebda74aa7be8d20b3131e43cccb@o4509091708928000.ingest.us.sentry.io/4509097435267072");
			options.setDebug(true);
			options.setSendDefaultPii(true);
			options.setRelease("my-app@1.2.3");
		});
		Sentry.captureMessage("✅ Test log from Spring Boot main method (integration check)");
	}

}
