package com.nexgencarrental.nexGenCarRental.services.rules.color;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import com.nexgencarrental.nexGenCarRental.repositories.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ColorBusinessRulesManager implements ColorBusinessRulesService {
    private ColorRepository colorRepository;
    @Override
    public void existsByName(String name) {
        if (colorRepository.existsByName(name.trim().replaceAll("\\s", ""))){
            throw new RuntimeException(ErrorConstants.COLOR_NAME_ALREADY_EXISTS);
        }
    }
}
