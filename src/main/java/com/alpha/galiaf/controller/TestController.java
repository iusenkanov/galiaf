package com.alpha.galiaf.controller;

import io.sentry.Sentry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

@GetMapping("/sentry-test")
public String sentryTest() {
    try {
        throw new RuntimeException("🚨 This is a test exception for Sentry");
    } catch (Exception e) {
        Sentry.captureException(e); 
        throw e; 
    }
}
    @GetMapping("/sentry-test3")
    public String sentryTest3() {
        try {
            throw new RuntimeException("new sentry error version 3");
        } catch (Exception e) {
            Sentry.captureException(e);
            throw e;
        }
    }
}