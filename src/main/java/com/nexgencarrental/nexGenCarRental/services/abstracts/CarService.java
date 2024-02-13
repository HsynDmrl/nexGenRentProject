package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.DeleteBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.DeleteCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;

public interface CarService extends BaseService<Car, CarRepository, GetCarResponse,
        GetCarListResponse, AddCarRequest, UpdateCarRequest> {
    void customAdd(AddCarRequest addColorRequest);

    void customUpdate(UpdateCarRequest updateColorRequest);
    void customDelete(DeleteCarRequest deleteCarRequest);

}
