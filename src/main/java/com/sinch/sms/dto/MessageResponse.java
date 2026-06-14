package com.sinch.sms.dto;

import com.sinch.sms.entity.MessageStatus;

import java.util.UUID;

public record MessageResponse(UUID id,
                              MessageStatus status) {
}
