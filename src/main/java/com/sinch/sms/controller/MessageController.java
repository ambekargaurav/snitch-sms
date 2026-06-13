package com.sinch.sms.controller;

import com.sinch.sms.dto.CreateMessageRequest;
import com.sinch.sms.dto.MessageResponse;
import com.sinch.sms.entity.Message;
import com.sinch.sms.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public MessageResponse create(
            @Valid @RequestBody CreateMessageRequest request) {

        Message message = service.create(request);

        return new MessageResponse(
                message.getId(),
                message.getStatus());
    }

    @GetMapping("/{id}")
    public Message get(
            @PathVariable Integer id) {
        return service.get(id);
    }
}
