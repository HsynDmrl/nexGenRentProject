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
@RequestMapping(ApiPathConstants.SEARCH_BASE_URL)
@AllArgsConstructor
public class SearchController {
    private final FilterRulesService filterRulesService;

    @GetMapping(ApiPathConstants.CAR_SEARCH_URL)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GetCarFilterResponse>> findAvailableByNames(
            @RequestParam(value = "searchTerm", required = false) String searchTerm) {
        List<Car> cars = filterRulesService.findAvailableCarsByNames(searchTerm);
        List<GetCarFilterResponse> response = cars.stream()
                .map(filterRulesService::convertToGetCarFilterResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
