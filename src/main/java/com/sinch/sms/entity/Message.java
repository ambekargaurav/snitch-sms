package com.sinch.sms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "t_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String destinationNumber;

    private String content;

    private String format;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    private Instant createdAt;
}
