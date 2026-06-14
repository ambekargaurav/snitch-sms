package com.sinch.sms.entity;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    private UUID id;

    private String destinationNumber;

    private String content;

    private String format;

    private Carrier carrier;

    private MessageStatus status;

    private Instant createdAt;
}
