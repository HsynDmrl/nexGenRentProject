package com.nexgencarrental.nexGenCarRental.services.rules.car;

public interface CarBusinessRulesService {
    void existsByPlate(String plate);
    void checkIfKilometerChanged(int newKilometer, int existingKilometer);
    void checkIfYearChanged(int newYear, int existingYear);
    void checkIfImagePathChanged(String newImagePath, String existingImagePath);
    void checkIfGearTypeChanged(String newGearType, String existingGearType);
    void checkIfFuelTypeChanged(String newFuelType, String existingFuelType);
    void checkIfModelIdChanged(int newModelId, int existingModelId);
    void checkIfColorIdChanged(int newColorId, int existingColorId);
    void checkIfPlateChanged(String newPlate, String existingPlate);
}
