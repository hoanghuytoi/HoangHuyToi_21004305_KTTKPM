package com.example.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public Exchange productExchange() {
        return new TopicExchange("product-exchange");
    }

    @Bean
    public Queue productQueue() {
        return new Queue("product-queue");
    }

    @Bean
    public Binding binding(Queue productQueue, Exchange productExchange) {
        return BindingBuilder
                .bind(productQueue)
                .to(productExchange)
                .with("product.*")
                .noargs();
    }
} 