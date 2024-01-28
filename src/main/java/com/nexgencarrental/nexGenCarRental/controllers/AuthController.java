package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.services.abstracts.AuthService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RefreshTokenService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.RefreshTokenRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.CreateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest createUserRequest) {
        authService.register(createUserRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        Map<String, String> tokens = authService.login(loginRequest);
        return ResponseEntity.ok().body(tokens);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshAccessToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        Map<String, String> tokens = refreshTokenService.refreshAccessToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok(tokens);
    }
}