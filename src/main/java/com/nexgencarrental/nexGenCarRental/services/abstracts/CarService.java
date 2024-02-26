package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarService extends BaseService<Car, CarRepository, GetCarResponse,
        GetCarListResponse, AddCarRequest, UpdateCarRequest> {
    GetCarFilterResponse customAdd(AddCarRequest addCarRequest, List<MultipartFile> images);

    GetCarFilterResponse customUpdate(UpdateCarRequest updateCarRequest, List<MultipartFile> images);

    void customDelete(int id);
}
