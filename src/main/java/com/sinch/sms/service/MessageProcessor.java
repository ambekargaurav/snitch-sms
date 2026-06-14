package com.sinch.sms.service;

import com.sinch.sms.entity.Carrier;
import com.sinch.sms.entity.Message;
import com.sinch.sms.entity.MessageStatus;
import com.sinch.sms.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Processes messages by validating phone numbers, routing to carriers, and updating status.
 */
@Slf4j
@Service
public class MessageProcessor {

    private final MessageRepository repository;
    private final PhoneNumberValidator validator;
    private final CarrierRoutingService router;

    public MessageProcessor(MessageRepository repository,
                            PhoneNumberValidator validator,
                            CarrierRoutingService router) {
        this.repository = repository;
        this.validator = validator;
        this.router = router;
    }

    public void process(UUID messageId) {
        log.debug("Processing message with id: {}", messageId);
        Message message = repository.findById(messageId).orElseThrow();
        validator.validate(message.getDestinationNumber());
        Carrier carrier = router.route(message.getDestinationNumber());
        message.setCarrier(carrier);
        message.setStatus(MessageStatus.SENT);
        repository.save(message);
        log.debug("Message processed successfully with id: {}", messageId);
    }
}
