package com.nexgencarrental.nexGenCarRental.services.rules.brand;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ErrorConstantException;
import com.nexgencarrental.nexGenCarRental.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants.BRAND_NAME_ALREADY_EXISTS;

@Service
@AllArgsConstructor
public class BrandBusinessRulesManager implements BrandBusinessRulesService {
    private final BrandRepository brandRepository;

    @Override
    public void existsByName(String name) {
        if (brandRepository.existsByName(name.trim().replaceAll("\\s", ""))) {
            throw new ErrorConstantException(BRAND_NAME_ALREADY_EXISTS);
        }
    }
}
