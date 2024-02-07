package com.nexgencarrental.nexGenCarRental.services.rules.car;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CarBusinessRulesManager implements CarBusinessRulesService {
    private CarRepository carRepository;
    @Override
    public void existsByPlate(String plate) {

        if (carRepository.existsByPlate(plate.trim().replaceAll("\\s", ""))){
            throw new ResponseStatusException(HttpStatus.CONFLICT,  ApplicationConstants.CAR_PLATE_ALREADY_EXISTS);
        }
    }

}
