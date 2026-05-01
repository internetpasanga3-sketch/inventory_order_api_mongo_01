package com.example.gateway.service;

import com.example.gateway.entity.ApiLog;
import com.example.gateway.repository.ApiLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiLogService {

    private final ApiLogRepository apiLogRepository;

    public void saveLog(String username,
                        String ip,
                        String method,
                        String endpoint,
                        int statusCode,
                        boolean rateLimitViolated,
                        long responseTimeMs) {

        ApiLog log = ApiLog.builder()
                .timestamp(LocalDateTime.now())
                .username(username)
                .ipAddress(ip)
                .method(method)
                .endpoint(endpoint)
                .statusCode(statusCode)
                .rateLimitViolated(rateLimitViolated)
                .responseTimeMs(responseTimeMs)
                .build();

        apiLogRepository.save(log);
    }

    public List<ApiLog> getAllLogs() {
        return apiLogRepository.findAll();
    }

    public List<ApiLog> getRateLimitViolations() {
        return apiLogRepository.findByRateLimitViolatedTrue();
    }

    public long getTotalRequests() {
        return apiLogRepository.count();
    }

    public long getSuccessCount() {
        return apiLogRepository.countByStatusCode(200);
    }

    public long getUnauthorizedCount() {
        return apiLogRepository.countByStatusCode(401);
    }

    public long getRateLimitedCount() {
        return apiLogRepository.countByStatusCode(429);
    }
}
