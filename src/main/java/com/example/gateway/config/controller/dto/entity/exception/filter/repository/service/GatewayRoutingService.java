package com.example.gateway.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GatewayRoutingService {

    public List<Map<String, Object>> routeToProducts() {
        return List.of(
                Map.of("id", 1, "name", "Laptop", "price", 55000, "category", "Electronics"),
                Map.of("id", 2, "name", "Phone", "price", 25000, "category", "Electronics"),
                Map.of("id", 3, "name", "Keyboard", "price", 1500, "category", "Accessories")
        );
    }

    public List<Map<String, Object>> routeToOrders() {
        return List.of(
                Map.of("id", 101, "product", "Laptop", "quantity", 2, "status", "PLACED"),
                Map.of("id", 102, "product", "Phone", "quantity", 1, "status", "SHIPPED")
        );
    }

    public List<Map<String, Object>> routeToUsers() {
        return List.of(
                Map.of("id", 1, "username", "ashwin", "role", "USER"),
                Map.of("id", 2, "username", "admin", "role", "ADMIN")
        );
    }
}
