package com.nexgencarrental.nexGenCarRental.repositories;

import com.nexgencarrental.nexGenCarRental.entities.concretes.RefreshToken;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
    int deleteByUser(User user);
}