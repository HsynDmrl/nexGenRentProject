package com.nexgencarrental.nexGenCarRental.services.rules.brand;

public interface BrandBusinessRulesService {
    void checkIfBrandNameExists(String name);

    void checkIfBrandNameExistsOnUpdate(String name, int id);

    void deleteBrandWithModels(int brandId);
}
