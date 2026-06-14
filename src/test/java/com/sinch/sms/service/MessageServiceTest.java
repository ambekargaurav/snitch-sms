package com.sinch.sms.service;

import com.sinch.sms.dto.CreateMessageRequest;
import com.sinch.sms.entity.Message;
import com.sinch.sms.entity.MessageStatus;
import com.sinch.sms.exception.MessageNotFoundException;
import com.sinch.sms.repository.InMemoryMessageRepository;
import com.sinch.sms.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MessageServiceTest {
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MessageRepository repository = new InMemoryMessageRepository();
        ApplicationEventPublisher publisher = org.mockito.Mockito.mock(ApplicationEventPublisher.class);
        this.messageService = new MessageService(repository, publisher);
    }

    /**
     * Verifies that a newly created message is in PENDING state.
     */
    @Test
    void shouldCreateMessageInPendingState() {
        CreateMessageRequest request =
                new CreateMessageRequest(
                        "+61491570156",
                        "Hello world",
                        "SMS"
                );

        Message message = messageService.create(request);
        assertThat(message.getStatus()).isEqualTo(MessageStatus.PENDING);
    }

    /**
     * Verifies that a newly created message has a unique ID.
     */
    @Test
    void shouldAssignUniqueMessageIdWhenCreated() {

        CreateMessageRequest request =
                new CreateMessageRequest(
                        "+61491570156",
                        "Hello world",
                        "SMS"
                );

        Message message = messageService.create(request);

        assertThat(message.getId()).isNotNull();
    }

    /**
     * Verifies that a message can be retrieved using its unique ID.
     */
    @Test
    void shouldRetrieveMessageUsingReturnedMessageId() {

        CreateMessageRequest request =
                new CreateMessageRequest(
                        "+61491570156",
                        "Hello world",
                        "SMS"
                );

        Message created = messageService.create(request);

        Message retrieved = messageService.get(created.getId());

        assertThat(retrieved.getId()).isEqualTo(created.getId());
        assertThat(retrieved.getDestinationNumber()).isEqualTo("+61491570156");
        assertThat(retrieved.getContent()).isEqualTo("Hello world");
    }

    /**
     * Verifies that a message is not found when the ID does not exist.
     */
    @Test
    void shouldReturnNotFoundWhenMessageDoesNotExist() {

        UUID unknownId = UUID.randomUUID();
        assertThatThrownBy(() ->
                messageService.get(unknownId))
                .isInstanceOf(MessageNotFoundException.class)
                .hasMessageContaining("Message not found");
    }
}
