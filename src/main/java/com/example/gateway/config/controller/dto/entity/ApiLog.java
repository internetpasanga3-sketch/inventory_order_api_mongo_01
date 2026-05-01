package com.example.gateway.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "api_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiLog {
    @Id
    private String id;
    private LocalDateTime timestamp;
    private String username;
    private String ipAddress;
    private String method;
    private String endpoint;
    private int statusCode;
    private boolean rateLimitViolated;
    private long responseTimeMs;
}
