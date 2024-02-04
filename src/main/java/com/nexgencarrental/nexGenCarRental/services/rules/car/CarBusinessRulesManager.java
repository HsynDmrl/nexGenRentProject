package com.nexgencarrental.nexGenCarRental.services.rules.car;

import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.ErrorConstants;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRulesManager implements CarBusinessRulesService {
    private CarRepository carRepository;
    @Override
    public void existsByPlate(String plate) {

        if (carRepository.existsByPlate(plate.trim().replaceAll("\\s", ""))){
            throw new RuntimeException(plate + ErrorConstants.CAR_PLATE_ALREADY_EXISTS);
        }
    }

}
