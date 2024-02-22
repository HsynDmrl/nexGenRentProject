package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService extends BaseService<Car, CarRepository, GetCarResponse,
        GetCarListResponse, AddCarRequest, UpdateCarRequest> {
    void customAdd(AddCarRequest addColorRequest);

    void customUpdate(UpdateCarRequest updateColorRequest);

    void customDelete(int id);

    List<Car> getAllEntityFilter (Integer brandId, Integer modelId, Short year, Integer colorId,String gearType, String fuelType, Double minDailyPrice, Double maxDailyPrice);

    List<Car> findAvailableCarsByNames(String searchTerm);

    GetCarFilterResponse createCarWithImages(AddCarRequest request, List<MultipartFile> images) throws IOException;

    GetCarFilterResponse convertToGetCarFilterResponse(Car car);

}
