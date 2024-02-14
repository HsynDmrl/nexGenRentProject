package com.nexgencarrental.nexGenCarRental.services.rules.car;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Model;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import com.nexgencarrental.nexGenCarRental.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.BRAND_NAME_ALREADY_EXISTS;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.DATA_CONFLICT;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ENTITY_NOT_FOUND;

@Service
@AllArgsConstructor
public class CarBusinessRulesManager implements CarBusinessRulesService {
    private CarRepository carRepository;
    private final ModelRepository modelRepository;
    private RentalRepository rentalRepository;


    @Override
    public void existsByPlate(String plate) {
        if (carRepository.existsByPlate(plate.trim().replaceAll("\\s", ""))) {
            throw new ConflictException(DATA_CONFLICT);
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
            Model model = car.getModel();

            car.setModel(null);
            carRepository.save(car);

            if (model != null) {
                modelRepository.delete(model);
            }

            carRepository.delete(car);
        } else {
            throw new DataNotFoundException(ENTITY_NOT_FOUND);
        }
    }
}
