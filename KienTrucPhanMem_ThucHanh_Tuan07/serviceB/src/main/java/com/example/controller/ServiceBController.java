package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceBController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/test")
    public String test() throws InterruptedException {
        // Simulate some processing time
        Thread.sleep(1000);
        return "Response from Service B";
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