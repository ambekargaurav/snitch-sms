package com.sinch.sms.service;

import com.sinch.sms.dto.CreateMessageRequest;
import com.sinch.sms.entity.Message;
import com.sinch.sms.entity.MessageStatus;
import com.sinch.sms.exception.MessageNotFoundException;
import com.sinch.sms.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
public class MessageService {
    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }
    public Message create(CreateMessageRequest request) {
        log.debug("Creating message with destination: {}", request.destinationNumber());

        Message message = new Message();

        message.setId(UUID.randomUUID());
        message.setDestinationNumber(request.destinationNumber());
        message.setContent(request.content());
        message.setFormat(request.format());
        message.setStatus(MessageStatus.PENDING);
        message.setCreatedAt(Instant.now());

        Message savedMessage = repository.save(message);
        log.debug("Message saved with id: {}", savedMessage.getId());
        return savedMessage;
    }

    public Message get(UUID id) {
        log.debug("Fetching message with id: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Message not found with id: {}", id);
                    return new MessageNotFoundException(
                            "Message not found: " + id);
                });
    }
}
