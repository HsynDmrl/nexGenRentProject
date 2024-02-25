package com.nexgencarrental.nexGenCarRental.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.FuelType;
import com.nexgencarrental.nexGenCarRental.entities.concretes.GearType;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RentalService;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.filter.FilterRulesService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiPathConstants.FILTER_BASE_URL)
@AllArgsConstructor
public class FilterController {

    private final FilterRulesService filterRulesService;
    private final RentalService rentalService;
    @GetMapping(ApiPathConstants.CAR_FILTER_URL)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetCarFilterResponse>> getByFilter(
            @RequestParam(required = false) Integer brandId,
            @RequestParam(required = false) Integer modelId,
            @RequestParam(required = false) Short year,
            @RequestParam(required = false) Integer colorId,
            @RequestParam(required = false) GearType gearType,
            @RequestParam(required = false) FuelType fuelType,
            @RequestParam(required = false) Double minDailyPrice,
            @RequestParam(required = false) Double maxDailyPrice) {

        List<Car> cars = filterRulesService.getAllEntityFilter(brandId, modelId, year, colorId, gearType, fuelType, minDailyPrice, maxDailyPrice);
        List<GetCarFilterResponse> response = cars.stream()
                .map(filterRulesService::convertToGetCarFilterResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiPathConstants.RENTAL_SEARCH_BY_DATES_URL)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetCarFilterResponse>> findAvailableByDates(
            @RequestParam @JsonFormat(pattern = "yyyy-MM-dd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @JsonFormat(pattern = "yyyy-MM-dd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Car> cars = rentalService.findAvailableByDates(startDate, endDate);
        List<GetCarFilterResponse> response = cars.stream()
                .map(filterRulesService::convertToGetCarFilterResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}