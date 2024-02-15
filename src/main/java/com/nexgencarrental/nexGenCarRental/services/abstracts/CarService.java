package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;

import java.util.List;

public interface CarService extends BaseService<Car, CarRepository, GetCarResponse,
        GetCarListResponse, AddCarRequest, UpdateCarRequest> {
    void customAdd(AddCarRequest addColorRequest);

    void customUpdate(UpdateCarRequest updateColorRequest);

    void customDelete(int id);

    List<Car> findAllEntityFilter(Integer brandId, Integer modelId, Double minDailyPrice, Double maxDailyPrice);

    List<Car> findAvailableCarsByNames(String searchTerm);
}
