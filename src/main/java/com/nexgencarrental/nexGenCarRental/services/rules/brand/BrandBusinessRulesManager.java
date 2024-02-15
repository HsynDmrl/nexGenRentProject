package com.nexgencarrental.nexGenCarRental.services.rules.brand;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Brand;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Model;
import com.nexgencarrental.nexGenCarRental.repositories.BrandRepository;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.BRAND_NAME_ALREADY_EXISTS;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ENTITY_NOT_FOUND;


@Service
@AllArgsConstructor
public class BrandBusinessRulesManager implements BrandBusinessRulesService {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    @Override
    public void checkIfBrandNameExists(String name) {
        if (brandRepository.existsByName(name.trim().replaceAll("\\s+", ""))) {
            throw new ConflictException(BRAND_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void checkIfBrandNameExistsOnUpdate(String name, int id) {
        if (brandRepository.existsByNameAndIdNot(name.trim().replaceAll("\\s+", ""), id)) {
            throw new ConflictException(BRAND_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteBrandWithModels(int brandId) {
        Optional<Brand> optionalBrand = brandRepository.findById(brandId);
        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            List<Model> models = brand.getModels();
            for (Model model : models) {
                model.setBrand(null);
                modelRepository.save(model);
            }
            brandRepository.delete(brand);
        } else {
            throw new DataNotFoundException(ENTITY_NOT_FOUND);
        }
    }
}
