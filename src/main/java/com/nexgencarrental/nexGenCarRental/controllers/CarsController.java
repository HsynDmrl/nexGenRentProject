package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPathConstants.CAR_BASE_URL)
@AllArgsConstructor
public class CarsController {
    private final CarService carService;
    @GetMapping(ApiPathConstants.GET_ALL_CARS)
    public List<GetCarListResponse> getAll(){
        return this.carService.getAll();
    }
    @GetMapping(ApiPathConstants.GET_CAR_BY_ID)
    public GetCarResponse getById(@PathVariable int id){
        return carService.getById(id);
    }
    @PostMapping(ApiPathConstants.ADD_CAR)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCarRequest addCarRequest) {
        this.carService.customAdd(addCarRequest);
    }
    @PutMapping(ApiPathConstants.UPDATE_CAR)
    public void update(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
        carService.customUpdate(updateCarRequest);
    }
    @DeleteMapping(ApiPathConstants.DELETE_CAR)
    public void delete(@PathVariable int id ) {
        this.carService.delete(id);
    }
}
