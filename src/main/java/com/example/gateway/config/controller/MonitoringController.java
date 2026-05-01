package com.example.gateway.controller;

import com.example.gateway.entity.ApiLog;
import com.example.gateway.service.ApiLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/monitor")
@RequiredArgsConstructor
public class MonitoringController {

    private final ApiLogService apiLogService;

    @GetMapping("/logs")
    public List<ApiLog> getAllLogs() {
        return apiLogService.getAllLogs();
    }

    @GetMapping("/logs/violations")
    public List<ApiLog> getViolations() {
        return apiLogService.getRateLimitViolations();
    }

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        return Map.of(
                "totalRequests", apiLogService.getTotalRequests(),
                "successfulRequests", apiLogService.getSuccessCount(),
                "unauthorizedRequests", apiLogService.getUnauthorizedCount(),
                "rateLimitedRequests", apiLogService.getRateLimitedCount()
        );
    }
}
