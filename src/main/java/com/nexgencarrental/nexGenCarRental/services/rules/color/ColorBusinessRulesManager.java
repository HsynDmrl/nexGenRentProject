package com.nexgencarrental.nexGenCarRental.services.rules.color;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Color;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.repositories.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.COLOR_NAME_ALREADY_EXISTS;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ENTITY_NOT_FOUND;

@Service
@AllArgsConstructor
public class ColorBusinessRulesManager implements ColorBusinessRulesService {
    private final ColorRepository colorRepository;
    private final CarRepository carRepository;

    @Override
    public void existsByName(String name) {
        if (colorRepository.existsByName(name.trim().replaceAll("\\s", ""))) {
            throw new ConflictException(COLOR_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteColorWithCars(int colorId) {
        Optional<Color> optionalColor = colorRepository.findById(colorId);
        if (optionalColor.isPresent()) {
            Color color = optionalColor.get();
            List<Car> cars = color.getCars();
            if (cars != null && !cars.isEmpty()) {
                for (Car car : cars) {
                    car.setColor(null);
                    carRepository.save(car);
                }
            }
            colorRepository.delete(color);
        } else {
            throw new DataNotFoundException(ENTITY_NOT_FOUND);
        }
    }
}
