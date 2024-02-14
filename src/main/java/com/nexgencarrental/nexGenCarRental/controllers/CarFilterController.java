package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/cars")
public class CarFilterController {


    private final CarService carService;

    public CarFilterController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/filter")
    public List<Car> getCarsByFilter(
            @RequestParam(required = false) Integer brandId,
            @RequestParam(required = false) Integer modelId,
            @RequestParam(required = false) Double minDailyPrice,
            @RequestParam(required = false) Double maxDailyPrice) {
        return carService.findAllEntityFilter(brandId, modelId, minDailyPrice, maxDailyPrice);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Car>> findAvailableCarsByNames(
            @RequestParam(value = "searchTerm", required = false) String searchTerm) {
        List<Car> cars = carService.findAvailableCarsByNames(searchTerm);
        return ResponseEntity.ok(cars);
    }
}