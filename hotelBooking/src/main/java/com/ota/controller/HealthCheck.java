package com.ota.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping(value = "/healthcheck")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("ok");
    }
}
