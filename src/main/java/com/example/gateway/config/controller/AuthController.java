package com.example.gateway.controller;

import com.example.gateway.dto.AuthRequest;
import com.example.gateway.dto.AuthResponse;
import com.example.gateway.dto.MessageResponse;
import com.example.gateway.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public MessageResponse register(@Valid @RequestBody AuthRequest request) {
        return new MessageResponse(authService.register(request));
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        return authService.login(request);
    }
}
