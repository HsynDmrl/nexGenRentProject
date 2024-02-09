package com.nexgencarrental.nexGenCarRental.services.rules.auth;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.UnauthorizedException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.USER_ALREADY_EXISTS;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class AuthBusinessRulesManager implements AuthBusinessRulesService {
    private final UserService userService;
    @Override
    public void validateRegistration(RegisterRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            throw new ConflictException(USER_ALREADY_EXISTS);
        }
    }

    @Override
    public void validateLogin(LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new DataNotFoundException(USER_NOT_FOUND));
    }
}
