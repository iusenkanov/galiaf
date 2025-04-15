package com.alpha.galiaf.controller;

import io.sentry.Sentry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/sentry-test")
    public String sentryTest() {
        int x = 5;
        int y = 10;
        int z = x + y;

        System.out.println("z = " + z);

        try {
            throw new Exception("🚨 This is a test exception for Sentry");
        } catch (Exception e) {
            Sentry.captureException(e);
        }

        return "Test exception sent to Sentry (if configured correctly)";
    }
}

//sdfdsfdsf
//asdfsdfsdf