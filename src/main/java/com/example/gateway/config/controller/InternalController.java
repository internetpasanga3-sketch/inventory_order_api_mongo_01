package com.example.gateway.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/internal")
public class InternalController {

    @GetMapping("/products")
    public List<Map<String, Object>> getProductsInternal() {
        return List.of(
                Map.of("id", 1, "name", "Laptop", "price", 55000, "category", "Electronics"),
                Map.of("id", 2, "name", "Phone", "price", 25000, "category", "Electronics"),
                Map.of("id", 3, "name", "Keyboard", "price", 1500, "category", "Accessories")
        );
    }

    @GetMapping("/orders")
    public List<Map<String, Object>> getOrdersInternal() {
        return List.of(
                Map.of("id", 101, "product", "Laptop", "quantity", 2, "status", "PLACED"),
                Map.of("id", 102, "product", "Phone", "quantity", 1, "status", "SHIPPED")
        );
    }

    @GetMapping("/users")
    public List<Map<String, Object>> getUsersInternal() {
        return List.of(
                Map.of("id", 1, "username", "ashwin", "role", "USER"),
                Map.of("id", 2, "username", "admin", "role", "ADMIN")
        );
    }
}
