package com.nexgencarrental.nexGenCarRental.services.rules.car;

public interface CarBusinessRulesService {
    void existsByPlate(String plate);
    void deleteCarWithModel(int carId);
}
