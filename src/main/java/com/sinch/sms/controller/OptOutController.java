package com.sinch.sms.controller;

import com.sinch.sms.service.OptOutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/optout")
public class OptOutController {
    private final OptOutService service;

    public OptOutController(OptOutService service) {
        this.service = service;
    }
    @PostMapping("/{phoneNumber}")
    public ResponseEntity<Void> optOut(
            @PathVariable String phoneNumber) {
        service.optOut(phoneNumber);
        return ResponseEntity.accepted().build();
    }
}
