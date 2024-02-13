package com.nexgencarrental.nexGenCarRental.services.rules.car;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.BRAND_NAME_ALREADY_EXISTS;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.DATA_CONFLICT;

@Service
@AllArgsConstructor
public class CarBusinessRulesManager implements CarBusinessRulesService {
    private CarRepository carRepository;

    @Override
    public void existsByPlate(String plate) {

        if (carRepository.existsByPlate(plate.trim().replaceAll("\\s", ""))) {
            throw new ConflictException(DATA_CONFLICT);
        }
    }

    @Override
    public void checkIfKilometerChanged(int newKilometer, int existingKilometer) {
        if (newKilometer != existingKilometer) {
            throw new ConflictException(BRAND_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void checkIfYearChanged(int newYear, int existingYear) {
        if (newYear != existingYear) {
            throw new ConflictException(BRAND_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void checkIfImagePathChanged(String newImagePath, String existingImagePath) {
        if (!newImagePath.equals(existingImagePath)) {
            throw new ConflictException(BRAND_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void checkIfGearTypeChanged(String newGearType, String existingGearType) {
        if (!newGearType.equals(existingGearType)) {
            throw new ConflictException(BRAND_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void checkIfFuelTypeChanged(String newFuelType, String existingFuelType) {
        if (!newFuelType.equals(existingFuelType)) {
            throw new ConflictException(BRAND_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void checkIfModelIdChanged(int newModelId, int existingModelId) {
        if (newModelId != existingModelId) {
            throw new ConflictException(BRAND_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void checkIfColorIdChanged(int newColorId, int existingColorId) {
        if (newColorId != existingColorId) {
            throw new ConflictException(BRAND_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void checkIfPlateChanged(String newPlate, String existingPlate) {
        if (!newPlate.equals(existingPlate)) {
            throw new ConflictException(DATA_CONFLICT);
        }
    }
}
