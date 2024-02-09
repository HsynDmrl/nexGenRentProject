package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.RefreshToken;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.auth.AuthResponse;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);

    RefreshToken createRefreshToken(int userId);

    void deleteByUserId(int userId);

    RefreshToken verifyExpiration(RefreshToken token);

    AuthResponse refreshAccessToken(String refreshToken);

}
