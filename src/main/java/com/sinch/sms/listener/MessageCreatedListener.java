package com.sinch.sms.listener;

import com.sinch.sms.dto.MessageCreatedEvent;
import com.sinch.sms.service.MessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Listens for MessageCreatedEvent and triggers message processing.
 */
@Slf4j
@Component
public class MessageCreatedListener {
    private final MessageProcessor processor;

    public MessageCreatedListener(MessageProcessor processor) {
        this.processor = processor;
    }
    @EventListener
    public void on(MessageCreatedEvent event) {
        log.debug("Received MessageCreatedEvent for message id: {}", event.messageId());
        processor.process(event.messageId());
        log.debug("MessageCreatedEvent processed for message id: {}", event.messageId());
    }
}
