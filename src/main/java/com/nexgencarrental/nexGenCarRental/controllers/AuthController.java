package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.services.abstracts.AuthService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RefreshTokenService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.RefreshTokenRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.RegisterRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.auth.AuthResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(ApiPathConstants.AUTH_BASE_URL)
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping(ApiPathConstants.REGISTER_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest registerRequest) {
            authService.register(registerRequest);
            return ResponseEntity.ok().build();
    }

    @PostMapping(ApiPathConstants.LOGIN_URL)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
            AuthResponse authResponse = authService.login(loginRequest);
            return ResponseEntity.ok(authResponse);
    }

    @PostMapping(ApiPathConstants.REFRESH_TOKEN_URL)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthResponse> refreshAccessToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
            AuthResponse authResponse = refreshTokenService.refreshAccessToken(refreshTokenRequest.getRefreshToken());
            return ResponseEntity.ok(authResponse);
    }
}
