package com.example.gateway.filter;

import com.example.gateway.exception.RateLimitExceededException;
import com.example.gateway.service.ApiLogService;
import com.example.gateway.service.RateLimiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RateLimitingFilter extends OncePerRequestFilter {

    private final RateLimiterService rateLimiterService;
    private final ApiLogService apiLogService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/auth")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        String username = (String) request.getAttribute("username");
        String ip = request.getRemoteAddr();
        String key = (username != null && !username.isBlank()) ? username : ip;

        if (!rateLimiterService.allowRequest(key)) {
            long responseTime = System.currentTimeMillis() - startTime;
            apiLogService.saveLog(
                    username,
                    ip,
                    request.getMethod(),
                    request.getRequestURI(),
                    429,
                    true,
                    responseTime
            );
            throw new RateLimitExceededException("Rate limit exceeded. Try again later.");
        }

        filterChain.doFilter(request, response);

        long responseTime = System.currentTimeMillis() - startTime;
        apiLogService.saveLog(
                username,
                ip,
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                false,
                responseTime
        );
    }
}
