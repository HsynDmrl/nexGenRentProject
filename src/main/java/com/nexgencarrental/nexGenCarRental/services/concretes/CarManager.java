package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ColorService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ModelService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.car.CarBusinessRulesService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ENTITY_NOT_FOUND;

@Service
public class CarManager extends BaseManager<Car, CarRepository, GetCarResponse, GetCarListResponse, AddCarRequest,
        UpdateCarRequest> implements CarService {
    private final ModelService modelService;
    private final ColorService colorService;
    private final CarBusinessRulesService carBusinessRulesService;
    private final CarRepository carRepository;

    public CarManager(CarRepository repository, ModelMapperService modelMapperService, ModelService modelService,
                      ColorService colorService, CarRepository carRepository, CarBusinessRulesService carBusinessRulesService) {
        super(repository, modelMapperService, GetCarResponse.class, GetCarListResponse.class, Car.class,
                AddCarRequest.class, UpdateCarRequest.class);
        this.modelService = modelService;
        this.colorService = colorService;
        this.carRepository = carRepository;
        this.carBusinessRulesService = carBusinessRulesService;
    }

    private void validateModelAndColorIds(int modelId, int colorId) {
        modelService.getById(modelId);
        colorService.getById(colorId);
    }

    @Override
    public void customAdd(AddCarRequest addCarRequest) {
        validateModelAndColorIds(addCarRequest.getModelId(), addCarRequest.getColorId());
        carBusinessRulesService.existsByPlate(addCarRequest.getPlate()); // PlateName kontrolü
        add(addCarRequest, Car.class);
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

    @Override
    public List<Car> findAllEntityFilter(Integer brandId, Integer modelId, Double minDailyPrice, Double maxDailyPrice) {
        return carRepository.findAllEntityFilter(brandId, modelId, minDailyPrice, maxDailyPrice);
    }

    @Override
    public List<Car> findAvailableCarsByNames(String searchTerm) {
        // Parametrenin `null` olup olmadığını kontrol edin ve
        // büyük/küçük harf duyarlılığını önlemek için `toLowerCase()` metodunu kullanarak
        // yüzde işaretleri (%) ile sarın.
        String searchPattern = (searchTerm != null && !searchTerm.trim().isEmpty())
                ? "%" + searchTerm.toLowerCase() + "%"
                : null;

        // Düzenlenmiş parametrelerle sorguyu çağırın.
        return carRepository.findAvailableCarsByNames(searchPattern);
    }
}
