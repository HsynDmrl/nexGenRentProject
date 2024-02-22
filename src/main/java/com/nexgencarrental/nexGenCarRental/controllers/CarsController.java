package com.nexgencarrental.nexGenCarRental.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ApiPathConstants.CAR_BASE_URL)
@AllArgsConstructor
public class CarsController {
    private final CarService carService;
    private final ObjectMapper objectMapper; // JSON nesnelerini işlemek için ObjectMapper

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

    @PostMapping(ApiPathConstants.ADD_CAR)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCarRequest addCarRequest) {
        this.carService.customAdd(addCarRequest);
    }

    @PutMapping(ApiPathConstants.UPDATE_CAR)
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
        carService.customUpdate(updateCarRequest);
    }

    // CarsController.java içinde

    // CarsController.java içinde

    @PostMapping(value = "/addCarWithImages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addCarWithImages(
            @RequestPart("car") String carString, // car nesnesini String olarak al
            @RequestPart(name = "images", required = false) List<MultipartFile> images) {
        try {
            AddCarRequest carRequest = objectMapper.readValue(carString, AddCarRequest.class); // String'i AddCarRequest'e çevir
            List<MultipartFile> imageList = images != null ? images : new ArrayList<>();
            GetCarFilterResponse savedCar = carService.createCarWithImages(carRequest, imageList);
            return ResponseEntity.ok().body(savedCar);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Araç ve resimler eklenirken bir hata oluştu: " + e.getMessage());
        }
    }

    @DeleteMapping(ApiPathConstants.DELETE_CAR)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        this.carService.customDelete(id);
    }
}