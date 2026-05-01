package com.example.gateway.repository;

import com.example.gateway.entity.ApiLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApiLogRepository extends MongoRepository<ApiLog, String> {
    List<ApiLog> findByRateLimitViolatedTrue();
    long countByStatusCode(int statusCode);
}
