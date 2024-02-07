package com.nexgencarrental.nexGenCarRental.services.rules.user;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ErrorConstantException;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants.USER_NAME_ALREADY_EXISTS;

@Service
@AllArgsConstructor
public class UserBusinessRulesManager implements UserBusinessRulesService {

    private UserRepository userRepository;
    @Override
    public void existsByName(String name) {
        if (userRepository.existsByName(name)){
            throw new ErrorConstantException(USER_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByNationalityId(String nationalityId) {
        if (userRepository.existsByNationalityId(nationalityId.trim().replaceAll("\\s", ""))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The Customer Nationality Id is already exists!");
        }
    }
}
