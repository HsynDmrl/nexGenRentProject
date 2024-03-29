package com.nexgencarrental.nexGenCarRental.services.rules.auth;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.RegisterRequest;

public interface AuthBusinessRulesService {
    void validateRegistration(RegisterRequest request);

    void validateLogin(LoginRequest loginRequest);
}
