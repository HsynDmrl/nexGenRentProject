package com.nexgencarrental.nexGenCarRental.services.rules.car;

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
            throw new RuntimeException(plate + " - This license plate is already in the system. Please enter a different license plate.");
        }
    }

}
