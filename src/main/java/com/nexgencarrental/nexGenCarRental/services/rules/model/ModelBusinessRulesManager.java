package com.nexgencarrental.nexGenCarRental.services.rules.model;

import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.ErrorConstants;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelBusinessRulesManager implements ModelBusinessRulesService{
    private ModelRepository modelRepository;
    @Override
    public void existsByName(String name) {
        if (modelRepository.existsByName(name.trim().replaceAll("\\s", ""))){
            throw new RuntimeException(ErrorConstants.MODEL_NAME_ALREADY_EXISTS);
        }
    }
}
