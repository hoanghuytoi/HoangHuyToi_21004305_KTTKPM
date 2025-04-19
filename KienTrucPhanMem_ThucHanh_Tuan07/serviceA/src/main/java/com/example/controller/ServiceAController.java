package com.example.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
public class ServiceAController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_B_URL = "http://localhost:8081";
    private static final String CIRCUIT_BREAKER_INSTANCE = "serviceA";
    private static final String RATE_LIMITER_INSTANCE = "serviceA";
    private static final String TIME_LIMITER_INSTANCE = "serviceA";
    private static final String RETRY_INSTANCE = "serviceA";

    @GetMapping("/test-cb")
    @CircuitBreaker(name = CIRCUIT_BREAKER_INSTANCE, fallbackMethod = "fallbackResponse")
    public String testCircuitBreaker() {
        return restTemplate.getForObject(SERVICE_B_URL + "/test", String.class);
    }

    @GetMapping("/test-rate-limit")
    @RateLimiter(name = RATE_LIMITER_INSTANCE)
    public String testRateLimit() {
        return "Request processed successfully";
    }

    @GetMapping("/test-time-limit")
    @TimeLimiter(name = TIME_LIMITER_INSTANCE)
    public CompletableFuture<String> testTimeLimit() {
        return CompletableFuture.supplyAsync(() -> {
            return restTemplate.getForObject(SERVICE_B_URL + "/test", String.class);
        });
    }

    @GetMapping("/test-retry")
    @Retry(name = RETRY_INSTANCE)
    public String testRetry() {
        return restTemplate.getForObject(SERVICE_B_URL + "/test", String.class);
    }

    public String fallbackResponse(Exception ex) {
        return "Fallback response due to service unavailability";
    }
} 