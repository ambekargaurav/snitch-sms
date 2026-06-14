package com.sinch.sms.dto;

import java.util.UUID;

public record MessageCreatedEvent(UUID messageId) {
}
