package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.services.JwtService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.RefreshToken;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.RefreshTokenRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RefreshTokenService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.auth.AuthResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenManager implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;
    private final JwtService jwtService;

    public RefreshTokenManager(RefreshTokenRepository refreshTokenRepository,
                               UserService userService, JwtService jwtService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        try {
            return refreshTokenRepository.findByToken(token);
        } catch (Exception ex) {
            throw new RuntimeException("Error during finding refresh token by token", ex);
        }
    }

    @Override
    public RefreshToken createRefreshToken(int userId) {
        try {
            User user = userService.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

            refreshTokenRepository.findByUser(user).ifPresent(refreshTokenRepository::delete);

            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setUser(user);
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(Instant.now().plusMillis(jwtService.refreshtokenms()));

            return refreshTokenRepository.save(refreshToken);
        } catch (IllegalArgumentException | DataAccessException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error during creating refresh token", ex);
        }
    }

    @Override
    public void deleteByUserId(int userId) {
        try {
            User user = userService.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
            refreshTokenRepository.deleteByUser(user);
        } catch (IllegalArgumentException | DataAccessException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error during deleting refresh tokens by user id", ex);
        }
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        try {
            if (token.getExpiryDate().isBefore(Instant.now())) {
                refreshTokenRepository.delete(token);
                throw new IllegalStateException("Refresh token was expired.");
            }
            return token;
        } catch (IllegalStateException | DataAccessException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error during verifying expiration of refresh token", ex);
        }
    }

    @Override
    public AuthResponse refreshAccessToken(String refreshTokenValue) {
        try {
            Optional<RefreshToken> refreshTokenOpt = findByToken(refreshTokenValue);
            RefreshToken refreshToken = refreshTokenOpt.orElseThrow(() ->
                    new IllegalStateException("Invalid Refresh Token"));
            refreshToken = verifyExpiration(refreshToken);
            User user = refreshToken.getUser();

            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getId());

            String newAccessToken = jwtService.generateToken(user.getUsername(), claims);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken(newAccessToken);
            authResponse.setRefreshToken(refreshToken.getToken());

            return authResponse;

        } catch (IllegalStateException | IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }
}