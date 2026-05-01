package com.example.gateway.service;

import com.example.gateway.util.TokenBucket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class RateLimiterService {

    @Value("${rate-limit.capacity}")
    private long capacity;

    @Value("${rate-limit.refill-tokens}")
    private long refillTokens;

    @Value("${rate-limit.refill-duration-seconds}")
    private long refillDurationSeconds;

    private final ConcurrentMap<String, TokenBucket> buckets = new ConcurrentHashMap<>();

    public boolean allowRequest(String key) {
        TokenBucket bucket = buckets.computeIfAbsent(
                key,
                k -> new TokenBucket(capacity, refillTokens, refillDurationSeconds * 1000)
        );
        return bucket.tryConsume();
    }
}
