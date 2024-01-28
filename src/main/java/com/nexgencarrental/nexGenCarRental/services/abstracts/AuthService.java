package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.RefreshToken;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.CreateUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface AuthService extends UserDetailsService {
    void register(CreateUserRequest request);
    Map<String, String> login(LoginRequest request);

}
