package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.InternalServerErrorException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.UnauthorizedException;
import com.nexgencarrental.nexGenCarRental.core.utilities.services.JwtService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.RefreshToken;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.RefreshTokenRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RefreshTokenService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.auth.AuthResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.*;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.InternalServerEnum.REFRESH_TOKEN_CREATION_ERROR;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.InternalServerEnum.TOKEN_GENERATION_ERROR;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.UnauthorizedEnum.EXPIRED_TOKEN;

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
            throw new DataNotFoundException(FIND_REFRESH_TOKEN_ERROR);
        }
    }

    @Override
    public RefreshToken createRefreshToken(int userId) {
        try {
            User user = userService.findById(userId)
                    .orElseThrow(() -> new DataNotFoundException(USER_NOT_FOUND));

            refreshTokenRepository.findByUser(user).ifPresent(refreshTokenRepository::delete);

            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setUser(user);
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(Instant.now().plusMillis(jwtService.refreshtokenms()));

            return refreshTokenRepository.save(refreshToken);
        } catch (DataNotFoundException ex) {
            throw new DataNotFoundException(USER_NOT_FOUND);
        } catch (Exception ex) {
            throw new InternalServerErrorException(REFRESH_TOKEN_CREATION_ERROR);
        }
    }

    @Override
    public void deleteByUserId(int userId) {
        try {
            User user = userService.findById(userId)
                    .orElseThrow(() -> new DataNotFoundException(USER_NOT_FOUND));
            refreshTokenRepository.deleteByUser(user);
        } catch (DataNotFoundException ex) {
            throw new DataNotFoundException(USER_NOT_FOUND);
        } catch (Exception ex) {
            throw new InternalServerErrorException(REFRESH_TOKEN_CREATION_ERROR);
        }
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        try {
            if (token.getExpiryDate().isBefore(Instant.now())) {
                refreshTokenRepository.delete(token);
                throw new InternalServerErrorException(REFRESH_TOKEN_CREATION_ERROR);
            }
            return token;
        } catch (Exception ex) {
            throw new InternalServerErrorException(REFRESH_TOKEN_CREATION_ERROR);
        }
    }

    @Override
    public AuthResponse refreshAccessToken(String refreshTokenValue) {
        try {
            Optional<RefreshToken> refreshTokenOpt = findByToken(refreshTokenValue);
            RefreshToken refreshToken = refreshTokenOpt.orElseThrow(() ->
                    new DataNotFoundException(REFRESH_TOKEN_NOT_FOUND));
            refreshToken = verifyExpiration(refreshToken);
            User user = refreshToken.getUser();

            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getId());

            String newAccessToken = jwtService.generateToken(user.getUsername(), claims);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken(newAccessToken);
            authResponse.setRefreshToken(refreshToken.getToken());

            return authResponse;

        } catch (DataNotFoundException ex) {
            throw new DataNotFoundException(REFRESH_TOKEN_NOT_FOUND);
        } catch (Exception ex) {
            throw new UnauthorizedException(EXPIRED_TOKEN);
        }
    }
}