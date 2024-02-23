package com.nexgencarrental.nexGenCarRental.services.rules.filter;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilterRulesManager implements FilterRulesService {

    private CarRepository carRepository;
    @Override
    public List<Car> findAvailableCarsByNames(String searchTerm) {
        String searchPattern = (searchTerm != null && !searchTerm.trim().isEmpty())
                ? "%" + searchTerm.toLowerCase() + "%"
                : null;
        return carRepository.findAvailableCarsByNames(searchPattern);
    }

    public List<Car> getAllEntityFilter(Integer brandId, Integer modelId, Short year,
                                        Integer colorId, String gearType, String fuelType, Double minDailyPrice, Double maxDailyPrice) {
        return carRepository.findAllEntityFilter(brandId, modelId, year, colorId, gearType, fuelType, minDailyPrice, maxDailyPrice);
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
