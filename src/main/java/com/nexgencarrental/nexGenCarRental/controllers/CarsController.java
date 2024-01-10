package com.nexgencarrental.nexGenCarRental.controllers;

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
@RequestMapping("api/cars")
@AllArgsConstructor
@CrossOrigin
public class CarsController {
    private final CarService carService;
    @GetMapping("/getAll")
    public List<GetCarListResponse> getAll(){
        return this.carService.getAll();
    }
    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable int id){
        return carService.getById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCarRequest addCarRequest) {
        this.carService.customAdd(addCarRequest);
    }
    @PutMapping("/update")
    public void update(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
        carService.customUpdate(updateCarRequest);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id ) {
        this.carService.delete(id);
    }
}
