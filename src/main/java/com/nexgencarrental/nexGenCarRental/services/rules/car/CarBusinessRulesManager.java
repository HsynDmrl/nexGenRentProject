package com.nexgencarrental.nexGenCarRental.services.rules.car;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Color;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Model;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.repositories.ColorRepository;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import com.nexgencarrental.nexGenCarRental.repositories.RentalRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarImgService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ColorService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ModelService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.PLATE_ALREADY_EXISTS;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ENTITY_NOT_FOUND;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.NO_CARS_FOUND;

@Service
@AllArgsConstructor
public class CarBusinessRulesManager implements CarBusinessRulesService {
    private CarRepository carRepository;
    private RentalRepository rentalRepository;
    private CarImgService carImgService;
    private ModelRepository modelRepository;
    private ColorRepository colorRepository;
    @Override
    public Car saveCar(AddCarRequest addCarRequest) {
        Car car = new Car();
        car.setKilometer(addCarRequest.getKilometer());
        car.setYear(addCarRequest.getYear());
        car.setDailyPrice(addCarRequest.getDailyPrice());
        car.setPlate(addCarRequest.getPlate());
        car.setGearType(addCarRequest.getGearType());
        car.setFuelType(addCarRequest.getFuelType());
        car.setStatus(addCarRequest.isStatus());

        Model model = modelRepository.findById(addCarRequest.getModelId())
                .orElseThrow(() -> new DataNotFoundException(ENTITY_NOT_FOUND));
        Color color = colorRepository.findById(addCarRequest.getColorId())
                .orElseThrow(() -> new DataNotFoundException(ENTITY_NOT_FOUND));
        car.setModel(model);
        car.setColor(color);

        return carRepository.save(car);
    }

    @Override
    public void existsByPlate(String plate) {
        if (carRepository.existsByPlate(plate.trim().replaceAll("\\s", ""))) {
            throw new ConflictException(PLATE_ALREADY_EXISTS);
        }
    }
    @Override
    public void uploadCarImages(List<MultipartFile> images, int carId) {
        if (images != null) {
            for (MultipartFile image : images) {
                carImgService.uploadCarImage(image, carId);
            }
        }
    }

    @Override
    public void deleteCarWithModel(int carId) {

        List<Rental> rentals = rentalRepository.findByCarId(carId);

        for (Rental rental : rentals) {
            rental.setCar(null);
            rentalRepository.save(rental);
        }

        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();

            car.setModel(null);
            carRepository.save(car);

            carRepository.delete(car);
        } else {
            throw new DataNotFoundException(NO_CARS_FOUND);
        }
    }
}
