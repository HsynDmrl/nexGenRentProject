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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final CarRepository carRepository;
    private final CarImgRepository carImgRepository; // CarImgRepository'yi ekleyin
    private final CarImgService carImgService;
    private final ColorRepository colorRepository;
    private final ModelRepository modelRepository;


    public CarManager(CarRepository carRepository, ModelMapperService modelMapperService,
                      ColorRepository colorRepository,ModelRepository modelRepository,
                      ModelService modelService, ColorService colorService, CarImgService carImgService,
                      CarBusinessRulesService carBusinessRulesService, CarImgRepository carImgRepository) {
        super(carRepository, modelMapperService, GetCarResponse.class, GetCarListResponse.class, Car.class, AddCarRequest.class, UpdateCarRequest.class);
        this.modelService = modelService;
        this.colorService = colorService;
        this.carBusinessRulesService = carBusinessRulesService;
        this.carRepository = carRepository;
        this.carImgRepository = carImgRepository; // CarImgRepository'yi başlatın
        this.carImgService = carImgService;
        this.colorRepository=colorRepository;
        this.modelRepository=modelRepository;
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
    public List<Car> getAllEntityFilter(Integer brandId, Integer modelId, Short year, Integer colorId,String gearType, String fuelType, Double minDailyPrice, Double maxDailyPrice) {
        return carRepository.findAllEntityFilter(brandId, modelId, year, colorId,gearType,fuelType, minDailyPrice, maxDailyPrice);
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

    @Override
    public Car createCarWithImages(AddCarRequest request, List<MultipartFile> images) throws IOException {
        // Car entity'sini AddCarRequest'ten gelen bilgilerle oluşturma ve kaydetme
        Car car = new Car();
        car.setKilometer(request.getKilometer());
        car.setYear(request.getYear());
        car.setDailyPrice(request.getDailyPrice());
        car.setPlate(request.getPlate());
        car.setGearType(request.getGearType());
        car.setFuelType(request.getFuelType());
        car.setStatus(request.isStatus()); // isStatus() metodu boolean türündeki getter metodudur.
        // Model ve Color entity'lerini ayarlama
        Model model = modelRepository.findById(request.getModelId())
                .orElseThrow(() -> new EntityNotFoundException("Model not found"));
        Color color = colorRepository.findById(request.getColorId())
                .orElseThrow(() -> new EntityNotFoundException("Color not found"));
        car.setModel(model);
        car.setColor(color);

        Car savedCar = carRepository.save(car);

        // Resimleri işleme ve CarImg entity'lerini kaydetme
        if (images != null) {
            for (MultipartFile image : images) {
                carImgService.uploadCarImage(image, savedCar.getId());
                // Eğer CarImg entity'nizde Car referansı tutuyorsanız, bu referansı burada ayarlayabilirsiniz.
            }
        }

        return savedCar;
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
