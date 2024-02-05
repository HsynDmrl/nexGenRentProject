package com.nexgencarrental.nexGenCarRental.services.rules.auth;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthBusinessRulesManager implements AuthBusinessRulesService{
    @Override
    public void validateRegistration(RegisterRequest request) {
        return;
    }
}
