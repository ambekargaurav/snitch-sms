package com.sinch.sms.service;

import com.sinch.sms.dto.CreateMessageRequest;
import com.sinch.sms.entity.Message;
import com.sinch.sms.entity.MessageStatus;
import com.sinch.sms.exception.MessageNotFoundException;
import com.sinch.sms.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class MessageService {
    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }
    public Message create(CreateMessageRequest request) {

        Message message = new Message();

        message.setDestinationNumber(request.destinationNumber());
        message.setContent(request.content());
        message.setFormat(request.format());
        message.setStatus(MessageStatus.PENDING);
        message.setCreatedAt(Instant.now());

        return repository.save(message);
    }

    public Message get(Integer id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new MessageNotFoundException(
                                "Message not found: " + id));
    }
}
