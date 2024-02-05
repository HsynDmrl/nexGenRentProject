package com.nexgencarrental.nexGenCarRental.services.rules.user;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRulesManager implements UserBusinessRulesService {

    private UserRepository userRepository;
    @Override
    public void existsByName(String name) {
        if (userRepository.existsByName(name)){
            throw new RuntimeException(ErrorConstants.USER_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByNationalityId(String name) {
        if (userRepository.existsByNationalityId(name.trim().replaceAll("\\s", ""))){
            throw new RuntimeException("The Customer Nationality Id is already exists!");
        }
    }
}
