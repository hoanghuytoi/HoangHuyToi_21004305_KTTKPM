package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ServiceBController {

    @Autowired
    private ApplicationContext context;
    
    private Random random = new Random();

    @GetMapping("/test")
    public String test(@RequestParam(required = false) Integer delay) throws InterruptedException {
        // Nếu có tham số delay, sử dụng nó, nếu không mặc định là 1000ms
        int sleepTime = delay != null ? delay : 1000;
        Thread.sleep(sleepTime);
        return "Response from Service B after " + sleepTime + "ms delay";
    }

    @GetMapping("/test-random-delay")
    public String testRandomDelay() throws InterruptedException {
        // Random delay từ 1 đến 3 giây để test Time Limiter
        int delay = 1000 + random.nextInt(2000);
        Thread.sleep(delay);
        return "Response from Service B after " + delay + "ms random delay";
    }

    @GetMapping("/test-retry")
    public String testRetry(@RequestParam(required = false) Integer failureRate) throws InterruptedException {
        // Mặc định 50% tỷ lệ lỗi nếu không có tham số
        int rate = failureRate != null ? failureRate : 50;
        
        // Giả lập lỗi ngẫu nhiên theo tỷ lệ
        if (random.nextInt(100) < rate) {
            throw new RuntimeException("Random failure for retry testing");
        }
        
        Thread.sleep(1000);
        return "Success response from Service B for retry test";
    }

    @GetMapping("/stop")
    public String stop() {
        // Thực hiện shutdown với delay 1 giây
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                SpringApplication.exit(context, () -> 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return "Service B is shutting down...";
    }
} 