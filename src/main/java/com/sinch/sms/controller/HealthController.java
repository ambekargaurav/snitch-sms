package com.sinch.sms.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@Slf4j
@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        log.debug("Health check endpoint called");
        return "SMS Service Running";
    }
}
