package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.entities.concretes.RefreshToken;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.RefreshTokenRepository;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RefreshTokenService;
import com.nexgencarrental.nexGenCarRental.core.utilities.services.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenManager implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final Long refreshTokenDurationMs;

    public RefreshTokenManager(RefreshTokenRepository refreshTokenRepository,
                               UserRepository userRepository,
                               JwtService jwtService,
                               @Value("${application.security.jwt.refreshtoken}") Long refreshTokenDurationMs) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.refreshTokenDurationMs = refreshTokenDurationMs;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void deleteByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        refreshTokenRepository.deleteByUser(user);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new IllegalStateException("Refresh token was expired.");
        }
        return token;
    }

    @Override
    public Map<String, String> refreshAccessToken(String refreshTokenValue) {
        Optional<RefreshToken> refreshTokenOpt = findByToken(refreshTokenValue);
        RefreshToken refreshToken = refreshTokenOpt.orElseThrow(() ->
                new IllegalStateException("Invalid Refresh Token"));
        refreshToken = verifyExpiration(refreshToken);
        User user = refreshToken.getUser();

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());

        String newAccessToken = jwtService.generateToken(user.getUsername(), claims);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", newAccessToken);
        tokens.put("refreshToken", refreshToken.getToken());

        return tokens;
    }
}