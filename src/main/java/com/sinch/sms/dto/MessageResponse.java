package com.sinch.sms.dto;

import com.sinch.sms.entity.MessageStatus;

public record MessageResponse(Integer id,
                              MessageStatus status) {
}
