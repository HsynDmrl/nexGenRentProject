package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.ApiPathConstants;
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
@RequestMapping(ApiPathConstants.BRANDS_BASE_URL)
@AllArgsConstructor
public class BrandsController {
    private final BrandService brandService;
    @GetMapping(ApiPathConstants.GET_ALL_BRANDS)
    public List<GetBrandListResponse> getAll(){
        return brandService.getAll();
    }
    @GetMapping(ApiPathConstants.GET_BRAND_BY_ID)
    public GetBrandResponse getById(int id){
        return brandService.getById(id);
    }
    @PostMapping(ApiPathConstants.ADD_BRAND)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddBrandRequest addBrandRequest) {
        this.brandService.customAdd(addBrandRequest);
    }
    @PutMapping(ApiPathConstants.UPDATE_BRAND)
    public void update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest){
        brandService.customUpdate(updateBrandRequest);
    }
    @DeleteMapping(ApiPathConstants.DELETE_BRAND)
    public void delete(@PathVariable int id){
        brandService.delete(id);
    }
}


