package com.nexgencarrental.nexGenCarRental.services.rules.filter;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;

import java.util.List;

public interface FilterRulesService {
    List<Car> findAvailableCarsByNames(String searchTerm);
    List<Car> getAllEntityFilter (Integer brandId, Integer modelId, Short year,
                                  Integer colorId,String gearType, String fuelType, Double minDailyPrice, Double maxDailyPrice);
    GetCarFilterResponse convertToGetCarFilterResponse(Car car);
}
