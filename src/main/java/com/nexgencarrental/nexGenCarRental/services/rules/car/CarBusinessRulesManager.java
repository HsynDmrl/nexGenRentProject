package com.nexgencarrental.nexGenCarRental.services.rules.car;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.DATA_CONFLICT;

@Service
@AllArgsConstructor
public class CarBusinessRulesManager implements CarBusinessRulesService {
    private CarRepository carRepository;

    @Override
    public void existsByPlate(String plate) {

        if (carRepository.existsByPlate(plate.trim().replaceAll("\\s", ""))) {
            throw new ConflictException(DATA_CONFLICT);
        }
    }
}
