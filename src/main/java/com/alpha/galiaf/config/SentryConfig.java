package com.alpha.galiaf.config;

import io.sentry.Sentry;
import io.sentry.SentryOptions;
import io.sentry.SentryEvent;
import io.sentry.protocol.SentryException;
import io.sentry.protocol.SentryStackFrame;
import io.sentry.protocol.SentryStackTrace;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class SentryConfig {

    @PostConstruct
    public void configureSentry() {
        SentryOptions options = Sentry.getCurrentHub().getOptions();
        options.setBeforeSend((event, hint) -> {
            if (event.getExceptions() != null) {
                for (SentryException exception : event.getExceptions()) {
                    SentryStackTrace trace = exception.getStacktrace();
                    if (trace != null) {
                        for (SentryStackFrame frame : trace.getFrames()) {
                            if (frame.getFilename() != null && frame.getFilename().endsWith(".java")) {
                                String classPath = frame.getModule(); // com.alpha.galiaf.controller.TestController
                                if (classPath != null && !classPath.isEmpty()) {
                                    frame.setAbsPath(classPath.replace('.', '/') + ".java");
                                }
                            }
                        }
                    }
                }
            }
            return event;
        });
    }
}
