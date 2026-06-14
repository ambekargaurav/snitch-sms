package com.sinch.sms.controller;

import com.sinch.sms.dto.CreateMessageRequest;
import com.sinch.sms.dto.MessageResponse;
import com.sinch.sms.entity.Message;
import com.sinch.sms.service.MessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/messages")
@Tag(name = "Messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public MessageResponse create(
            @Valid @RequestBody CreateMessageRequest request) {
        log.info("Creating message for destination: {}", request.destinationNumber());
        Message message = service.create(request);
        log.info("Message created with id: {}", message.getId());

        return new MessageResponse(
                message.getId(),
                message.getStatus());
    }

    @GetMapping("/{id}")
    public Message get(
            @PathVariable UUID id) {
        log.debug("Fetching message with id: {}", id);
        return service.get(id);
    }
}
