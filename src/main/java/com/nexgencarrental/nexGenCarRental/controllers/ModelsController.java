package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.services.abstracts.ModelService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.AddModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.UpdateModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/models")
@AllArgsConstructor
@CrossOrigin
public class ModelsController {
    private final ModelService modelService;
    @GetMapping("/getAll")
    public List<GetModelListResponse> getAll(){
        return modelService.getAll();
    }
    @GetMapping("/{id}")
    public GetModelResponse getById(int id){
        return modelService.getById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddModelRequest addModelRequest) {
        this.modelService.customAdd(addModelRequest);
    }
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateModelRequest updateModelRequest){
        modelService.customUpdate(updateModelRequest);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        modelService.delete(id);
    }
}
