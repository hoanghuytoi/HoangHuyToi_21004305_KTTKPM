package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final RabbitTemplate rabbitTemplate;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        // Notify other services about new product
        rabbitTemplate.convertAndSend("product-exchange", "product.created", savedProduct);
        return savedProduct;
    }

    public Optional<Product> updateProduct(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            Product updatedProduct = productRepository.save(product);
            // Notify other services about product update
            rabbitTemplate.convertAndSend("product-exchange", "product.updated", updatedProduct);
            return Optional.of(updatedProduct);
        }
        return Optional.empty();
    }

    public boolean deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            // Notify other services about product deletion
            rabbitTemplate.convertAndSend("product-exchange", "product.deleted", id);
            return true;
        }
        return false;
    }
} 