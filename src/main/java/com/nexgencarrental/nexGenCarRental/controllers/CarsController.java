package com.nexgencarrental.nexGenCarRental.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(ApiPathConstants.CAR_BASE_URL)
@AllArgsConstructor
public class CarsController {
    private final CarService carService;
    private final ObjectMapper objectMapper;

    @GetMapping(ApiPathConstants.GET_ALL_CARS)
    @ResponseStatus(HttpStatus.OK)
    public List<GetCarListResponse> getAll() {
        return this.carService.getAll();
    }

    @GetMapping(ApiPathConstants.GET_CAR_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    public GetCarResponse getById(@PathVariable int id) {
        return carService.getById(id);
    }

    @PostMapping(value = ApiPathConstants.ADD_CAR, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> add(
            @RequestPart("car") String carString,
            @RequestPart(name = "images", required = false) List<MultipartFile> images) throws IOException {
        AddCarRequest carRequest = objectMapper.readValue(carString, AddCarRequest.class);
        GetCarFilterResponse savedCar = carService.customAdd(carRequest, images);
        return ResponseEntity.ok().body(savedCar);
    }

    @PutMapping(ApiPathConstants.UPDATE_CAR)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody UpdateCarRequest updateCarRequest) {
        carService.customUpdate(updateCarRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(ApiPathConstants.DELETE_CAR)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        this.carService.customDelete(id);
    }
}