package com.nexgencarrental.nexGenCarRental.services.rules.brand;

import com.nexgencarrental.nexGenCarRental.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRulesManager implements BrandBusinessRulesService {
    private BrandRepository brandRepository;
    @Override
    public void existsByName(String name) {

        if (brandRepository.existsByName(name.trim().replaceAll("\\s", ""))){
            throw new RuntimeException("The Brand name is already exists!");
        }
    }
}
