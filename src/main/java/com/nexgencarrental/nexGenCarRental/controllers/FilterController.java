package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.filter.FilterRulesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiPathConstants.CAR_BASE_URL)
@AllArgsConstructor
public class FilterController {

    private final FilterRulesService filterRulesService;
    @GetMapping(ApiPathConstants.CAR_FILTER_URL)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetCarFilterResponse>> getByFilter(
            @RequestParam(required = false) Integer brandId,
            @RequestParam(required = false) Integer modelId,
            @RequestParam(required = false) Short year,
            @RequestParam(required = false) Integer colorId,
            @RequestParam(required = false) String gearType,
            @RequestParam(required = false) String fuelType,
            @RequestParam(required = false) Double minDailyPrice,
            @RequestParam(required = false) Double maxDailyPrice) {

        List<Car> cars = filterRulesService.getAllEntityFilter(brandId, modelId, year, colorId, gearType, fuelType, minDailyPrice, maxDailyPrice);
        List<GetCarFilterResponse> response = cars.stream()
                .map(filterRulesService::convertToGetCarFilterResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}