package com.nexgencarrental.nexGenCarRental.services.rules.color;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.repositories.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.COLOR_NAME_ALREADY_EXISTS;

@Service
@AllArgsConstructor
public class ColorBusinessRulesManager implements ColorBusinessRulesService {
    private final ColorRepository colorRepository;

    @Override
    public void existsByName(String name) {
        if (colorRepository.existsByName(name.trim().replaceAll("\\s", ""))) {
            throw new ConflictException(COLOR_NAME_ALREADY_EXISTS);
        }
    }
}
