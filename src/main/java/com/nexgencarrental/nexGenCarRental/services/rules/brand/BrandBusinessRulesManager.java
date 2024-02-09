package com.nexgencarrental.nexGenCarRental.services.rules.brand;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.BRAND_NAME_ALREADY_EXISTS;


@Service
@AllArgsConstructor
public class BrandBusinessRulesManager implements BrandBusinessRulesService {
    private final BrandRepository brandRepository;

    @Override
    public void existsByName(String name) {
        if (brandRepository.existsByName(name.trim().replaceAll("\\s", ""))) {
            throw new ConflictException(BRAND_NAME_ALREADY_EXISTS);
        }
    }
}
