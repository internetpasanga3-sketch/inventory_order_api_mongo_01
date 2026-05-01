package com.example.gateway.util;

public class TokenBucket {

    private final long capacity;
    private final long refillTokens;
    private final long refillDurationMillis;

    private long availableTokens;
    private long lastRefillTimestamp;

    public TokenBucket(long capacity, long refillTokens, long refillDurationMillis) {
        this.capacity = capacity;
        this.refillTokens = refillTokens;
        this.refillDurationMillis = refillDurationMillis;
        this.availableTokens = capacity;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean tryConsume() {
        refill();

        if (availableTokens > 0) {
            availableTokens--;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long timeSinceLastRefill = now - lastRefillTimestamp;

        if (timeSinceLastRefill >= refillDurationMillis) {
            long tokensToAdd = (timeSinceLastRefill / refillDurationMillis) * refillTokens;
            availableTokens = Math.min(capacity, availableTokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }
}
