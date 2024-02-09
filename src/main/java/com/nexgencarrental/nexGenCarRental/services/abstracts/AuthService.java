package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.RegisterRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.auth.AuthResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    UserDetails loadUserByUsername(String username);
}
