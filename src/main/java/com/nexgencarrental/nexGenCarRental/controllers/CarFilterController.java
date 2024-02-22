package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarFilterController {


    private final CarService carService;

    public CarFilterController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/filter")
    public ResponseEntity<List<GetCarFilterResponse>> getCarsByFilter(
            @RequestParam(required = false) Integer brandId,
            @RequestParam(required = false) Integer modelId,
            @RequestParam(required = false) Short year,
            @RequestParam(required = false) Integer colorId,
            @RequestParam(required = false) String gearType,
            @RequestParam(required = false) String fuelType,
            @RequestParam(required = false) Double minDailyPrice,
            @RequestParam(required = false) Double maxDailyPrice) {

        List<Car> cars = carService.getAllEntityFilter(brandId, modelId, year, colorId, gearType, fuelType, minDailyPrice, maxDailyPrice);
        List<GetCarFilterResponse> response = cars.stream()
                .map(carService::convertToGetCarFilterResponse) // CarService içinde tanımlı olmalı
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetCarFilterResponse>> findAvailableCarsByNames(
            @RequestParam(value = "searchTerm", required = false) String searchTerm) {
        List<Car> cars = carService.findAvailableCarsByNames(searchTerm);
        List<GetCarFilterResponse> response = cars.stream()
                .map(carService::convertToGetCarFilterResponse) // CarService içinde tanımlı olmalı
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


}