package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.CreateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.auth.AuthResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    void register(CreateUserRequest request);
    AuthResponse login(LoginRequest request);

    UserDetails loadUserByUsername(String username);
}
