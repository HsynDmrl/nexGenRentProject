package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Color;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Model;
import com.nexgencarrental.nexGenCarRental.repositories.CarImgRepository;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.repositories.ColorRepository;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarImgService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ColorService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ModelService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.car.CarBusinessRulesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ENTITY_NOT_FOUND;

@Service
public class CarManager extends BaseManager<Car, CarRepository, GetCarResponse, GetCarListResponse, AddCarRequest,
        UpdateCarRequest> implements CarService {
    private final ModelService modelService;
    private final ColorService colorService;
    private final CarBusinessRulesService carBusinessRulesService;


    public CarManager(CarRepository carRepository, ModelMapperService modelMapperService,
                      ColorRepository colorRepository,ModelRepository modelRepository,
                      ModelService modelService, ColorService colorService, CarImgService carImgService,
                      CarBusinessRulesService carBusinessRulesService, CarImgRepository carImgRepository) {
        super(carRepository, modelMapperService, GetCarResponse.class, GetCarListResponse.class, Car.class, AddCarRequest.class, UpdateCarRequest.class);
        this.modelService = modelService;
        this.colorService = colorService;
        this.carBusinessRulesService = carBusinessRulesService;
    }

    private void validateModelAndColorIds(int modelId, int colorId) {
        modelService.getById(modelId);
        colorService.getById(colorId);
    }

    @Override
    @SneakyThrows
    public GetCarFilterResponse customAdd(AddCarRequest addCarRequest, List<MultipartFile> images){

        validateModelAndColorIds(addCarRequest.getModelId(), addCarRequest.getColorId());
        carBusinessRulesService.existsByPlate(addCarRequest.getPlate());
        Car savedCar = carBusinessRulesService.saveCar(addCarRequest);
        carBusinessRulesService.uploadCarImages(images, savedCar.getId());

        return modelMapperService.forResponse().map(savedCar, GetCarFilterResponse.class);
    }

    @Override
    public void customUpdate(UpdateCarRequest updateCarRequest) {
        validateModelAndColorIds(updateCarRequest.getModelId(), updateCarRequest.getColorId());
        Car existingCar = repository.findById(updateCarRequest.getId()).orElseThrow(() -> new DataNotFoundException(ENTITY_NOT_FOUND));
        update(updateCarRequest, Car.class);
    }

    @Override
    public void customDelete(int id) {
        carBusinessRulesService.deleteCarWithModel(id);
    }
}
