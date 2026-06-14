package com.sinch.sms.service;

import com.sinch.sms.entity.Carrier;
import com.sinch.sms.entity.Message;
import com.sinch.sms.entity.MessageStatus;
import com.sinch.sms.exception.InvalidPhoneNumberException;
import com.sinch.sms.repository.InMemoryMessageRepository;
import com.sinch.sms.repository.InMemoryOptOutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MessageProcessorTest {

    private MessageProcessor processor;
    private InMemoryMessageRepository repository;
    private PhoneNumberValidator validator;
    private CarrierRoutingService router;
    private OptOutService optOutService;

    @BeforeEach
    void setup() {
        repository = new InMemoryMessageRepository();
        validator = new PhoneNumberValidator();
        router = new CarrierRoutingService();
        optOutService = new OptOutService(new InMemoryOptOutRepository());
        processor = new MessageProcessor(repository, validator, router, optOutService);
    }

    @Test
    void shouldSetCarrierAndMarkMessageAsSent() {

        Message message = repository.save(
                Message.builder()
                        .id(UUID.randomUUID())
                        .destinationNumber("+61491570156")
                        .status(MessageStatus.PENDING)
                        .build()
        );

        processor.process(message.getId());

        Message updated = repository.findById(message.getId()).get();

        assertThat(updated.getStatus()).isEqualTo(MessageStatus.SENT);
        assertThat(updated.getCarrier()).isNotNull();
    }

    @Test
    void shouldAssignTelstraOrOptusForAustralianNumber() {

        Message message = repository.save(
                Message.builder()
                        .id(UUID.randomUUID())
                        .destinationNumber("+61491570156")
                        .status(MessageStatus.PENDING)
                        .build()
        );

        processor.process(message.getId());

        Message updated = repository.findById(message.getId()).get();

        assertThat(updated.getCarrier())
                .isIn(Carrier.TELSTRA, Carrier.OPTUS);
    }

    @Test
    void shouldThrowExceptionForInvalidPhoneNumber() {

        Message message = repository.save(
                Message.builder()
                        .id(UUID.randomUUID())
                        .destinationNumber("123")
                        .status(MessageStatus.PENDING)
                        .build()
        );

        assertThatThrownBy(() -> processor.process(message.getId()))
                .isInstanceOf(InvalidPhoneNumberException.class);
    }

    @Test
    void shouldBlockMessageForOptedOutNumber() {

        optOutService.optOut("+61491570156");

        Message message = repository.save(
                Message.builder()
                        .id(UUID.randomUUID())
                        .destinationNumber("+61491570156")
                        .content("Hello")
                        .status(MessageStatus.PENDING)
                        .build());

        processor.process(message.getId());

        Message updated =
                repository.findById(message.getId())
                        .orElseThrow();

        assertThat(updated.getStatus())
                .isEqualTo(MessageStatus.BLOCKED);
    }


}
