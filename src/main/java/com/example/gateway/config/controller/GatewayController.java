package com.example.gateway.controller;

import com.example.gateway.service.GatewayRoutingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gateway")
@RequiredArgsConstructor
public class GatewayController {

    private final GatewayRoutingService gatewayRoutingService;

    @GetMapping("/products")
    public List<Map<String, Object>> getProducts() {
        return gatewayRoutingService.routeToProducts();
    }

    @GetMapping("/orders")
    public List<Map<String, Object>> getOrders() {
        return gatewayRoutingService.routeToOrders();
    }

    @GetMapping("/users")
    public List<Map<String, Object>> getUsers() {
        return gatewayRoutingService.routeToUsers();
    }
}
