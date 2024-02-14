package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.RegisterRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.UpdatePasswordRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.auth.AuthResponse;

public interface AuthService {
    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
    void updatePasswordForUser(UpdatePasswordRequest updatePasswordRequest);
}
