package com.alpha.galiaf;

import io.sentry.Sentry;
import io.sentry.protocol.SentryException;
import io.sentry.protocol.SentryStackFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GaliafApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaliafApplication.class, args);
		Sentry.init(options -> {
			options.setDsn("https://a3730c176137847d3dea63b5b0fa9447@o4509091708928000.ingest.us.sentry.io/4509091710042112");
			options.setRelease("galiaf@1.0.3");
			options.addInAppInclude("com.alpha.galiaf");
			options.setBeforeSend((event, hint) -> {
				if (event.getExceptions() != null) {
					for (SentryException ex : event.getExceptions()) {
						for (SentryStackFrame frame : ex.getStacktrace().getFrames()) {
							if ("TestController.java".equals(frame.getFilename())) {
								frame.setFilename("src/main/java/com/alpha/galiaf/controller/TestController.java");
								frame.setAbsPath("src/main/java/com/alpha/galiaf/controller/TestController.java");
							}
						}
					}
				}
				return event;
			});
		});
		Sentry.captureMessage("✅ Test log from Spring Boot main method (integration check)");
	}

}
