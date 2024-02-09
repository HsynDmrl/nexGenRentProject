package com.nexgencarrental.nexGenCarRental.services.rules.model;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.MODEL_NAME_ALREADY_EXISTS;

@Service
@AllArgsConstructor
public class ModelBusinessRulesManager implements ModelBusinessRulesService {
    private final ModelRepository modelRepository;

    @Override
    public void existsByName(String name) {
        if (modelRepository.existsByName(name.trim().replaceAll("\\s", ""))) {
            throw new ConflictException(MODEL_NAME_ALREADY_EXISTS);
        }
    }
}
