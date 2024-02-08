package com.nexgencarrental.nexGenCarRental.services.rules.color;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ErrorConstantException;
import com.nexgencarrental.nexGenCarRental.repositories.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants.COLOR_NAME_ALREADY_EXISTS;

@Service
@AllArgsConstructor
public class ColorBusinessRulesManager implements ColorBusinessRulesService {
    private final ColorRepository colorRepository;

    @Override
    public void existsByName(String name) {
        if (colorRepository.existsByName(name.trim().replaceAll("\\s", ""))) {
            throw new DataNotFoundException(DataNotFoundConstants.NO_USER_FOUND);
        }
    }
}
