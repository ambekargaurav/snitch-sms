package com.sinch.sms.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptOut {
    private String phoneNumber;
}
