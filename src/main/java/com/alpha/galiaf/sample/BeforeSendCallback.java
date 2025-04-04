package com.alpha.galiaf.sample;

import io.sentry.Hint;
import io.sentry.SentryEvent;
import io.sentry.SentryOptions;
import io.sentry.protocol.SentryException;
import io.sentry.protocol.SentryStackFrame;

public class BeforeSendCallback implements SentryOptions.BeforeSendCallback {
    @Override
    public SentryEvent execute(SentryEvent event, Hint hint) {
        if (event.getExceptions() != null) {
            for (SentryException ex : event.getExceptions()) {
                if (ex.getStacktrace() != null && ex.getStacktrace().getFrames() != null) {
                    for (SentryStackFrame frame : ex.getStacktrace().getFrames()) {
                        if ("TestController.java".equals(frame.getFilename())) {
                            frame.setFilename("src/main/java/com/alpha/galiaf/controller/TestController.java");
                            frame.setAbsPath("src/main/java/com/alpha/galiaf/controller/TestController.java");
                        }
                    }
                }
            }
        }
        return event;
    }
}
