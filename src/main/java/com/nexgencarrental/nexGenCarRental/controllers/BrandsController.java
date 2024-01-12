package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.services.abstracts.BrandService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.AddBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.UpdateBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brands")
@AllArgsConstructor
@CrossOrigin
public class BrandsController {
    private final BrandService brandService;
    @GetMapping("/getAll")
    public List<GetBrandListResponse> getAll(){
        return brandService.getAll();
    }
    @GetMapping("/{id}")
    public GetBrandResponse getById(int id){
        return brandService.getById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddBrandRequest addBrandRequest) {
        this.brandService.customAdd(addBrandRequest);
    }
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest){
        brandService.customUpdate(updateBrandRequest);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        brandService.delete(id);
    }
}


