package com.nexgencarrental.nexGenCarRental.services.rules.car;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarBusinessRulesService {
    Car saveCar(AddCarRequest addCarRequest);

    void existsByPlate(String plate);

    void uploadCarImages(List<MultipartFile> images, int carId);

    void deleteCarWithModel(int carId);
}
