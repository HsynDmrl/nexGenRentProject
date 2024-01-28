package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.RefreshToken;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;

import java.util.Map;
import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(int userId);
    void deleteByUserId(int userId);
    RefreshToken verifyExpiration(RefreshToken token);
    Map<String, String> refreshAccessToken(String refreshToken);
}