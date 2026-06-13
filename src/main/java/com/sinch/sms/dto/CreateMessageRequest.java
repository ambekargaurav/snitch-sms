package com.sinch.sms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CreateMessageRequest(@NotBlank
                                   @JsonProperty("destination_number")
                                   String destinationNumber,

                                   @NotBlank
                                   String content,

                                   @NotBlank
                                   String format) {
}
