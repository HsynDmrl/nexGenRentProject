package com.nexgencarrental.nexGenCarRental.services.rules.user;


import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.DATA_CONFLICT;


@Service
@AllArgsConstructor
public class UserBusinessRulesManager implements UserBusinessRulesService {

    private UserRepository userRepository;

    @Override
    public void existsByName(String name) {
        if (userRepository.existsByName(name)) {
            throw new ConflictException(DATA_CONFLICT);
        }
    }

    @Override
    public void existsByNationalityId(String nationalityId) {
        if (userRepository.existsByNationalityId(nationalityId.trim().replaceAll("\\s", ""))) {
            throw new ConflictException(DATA_CONFLICT);
        }
    }
}
