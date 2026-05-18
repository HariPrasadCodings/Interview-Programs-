package com.interview.microservices.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Long id;
    private Long userId;
    private String type;
    private String subject;
    private String message;
    private String channel; // EMAIL, SMS, PUSH
    private boolean sent;
    private LocalDateTime createdAt;
}
