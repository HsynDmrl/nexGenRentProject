package com.nexgencarrental.nexGenCarRental.services.rules.filter;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorEnum;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.FuelType;
import com.nexgencarrental.nexGenCarRental.entities.concretes.GearType;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.*;

@Service
@AllArgsConstructor
public class FilterRulesManager implements FilterRulesService {

    private CarRepository carRepository;
    @Override
    public List<Car> findAvailableCarsByNames(String searchTerm) {
        String searchPattern = (searchTerm != null && !searchTerm.trim().isEmpty())
                ? "%" + searchTerm.toLowerCase() + "%"
                : null;

        if (searchPattern == null) {
            throw new IllegalArgumentException(String.valueOf(ErrorEnum.SEARCH_RESPONSE_ERROR));
        }
        List<Car> foundCars = carRepository.findAvailableCarsByNames(searchPattern);

        if (foundCars.isEmpty()) {
            throw new DataNotFoundException(ENTITY_NOT_FOUND);
        }

        return foundCars;
    }

    public List<Car> getAllEntityFilter(Integer brandId, Integer modelId, Short year,
                                        Integer colorId, GearType gearType, FuelType fuelType, Double minDailyPrice, Double maxDailyPrice) {

        if (brandId == null) {
            throw new DataNotFoundException(NO_BRAND_FOUND);
        }
        if (modelId == null) {
            throw new DataNotFoundException(NO_MODEL_FOUND);
        }
        if (year == null) {
            throw new DataNotFoundException(NO_YEAR_FOUND);
        }
        if (colorId == null) {
            throw new DataNotFoundException(NO_COLOR_FOUND);
        }
        if (gearType == null) {
            throw new DataNotFoundException(NO_GEAR_FOUND);
        }
        if (fuelType == null) {
            throw new DataNotFoundException(NO_FUEL_FOUND);
        }
        if (minDailyPrice == null || maxDailyPrice == null) {
            throw new DataNotFoundException(RANGE_NOT_FOUND);
        }

        List<Car> filteredCars = carRepository.findAllEntityFilter(brandId, modelId, year, colorId, gearType, fuelType, minDailyPrice, maxDailyPrice);

        if (filteredCars.isEmpty()) {
            throw new DataNotFoundException(NO_FILTER_CAR);
        }

        return filteredCars;
    }

    @Override
    public GetCarFilterResponse convertToGetCarFilterResponse(Car car) {
        GetCarFilterResponse response = new GetCarFilterResponse();
        response.setId(car.getId());
        response.setKilometer(car.getKilometer());
        response.setYear(car.getYear());
        response.setDailyPrice(car.getDailyPrice());
        response.setPlate(car.getPlate());
        response.setImagePath(car.getImagePath());
        response.setStatus(car.isStatus());
        response.setGearType(car.getGearType().name());
        response.setFuelType(car.getFuelType().name());
        response.setModelName(car.getModel().getName());
        response.setColorName(car.getColor().getName());
        response.setBrandName(car.getModel().getBrand().getName());
        return response;
    }
}
