package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.CreateUserRequest;

public interface AuthService {
    void register(CreateUserRequest createUserRequest);
    String login(LoginRequest loginRequest);
}
