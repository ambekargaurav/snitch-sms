package com.sinch.sms.repository;

import com.sinch.sms.entity.Message;

import java.util.Optional;
import java.util.UUID;

public interface MessageRepository {
    Message save(Message message);

    Optional<Message> findById(UUID id);
}
