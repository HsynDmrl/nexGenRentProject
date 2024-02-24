package com.nexgencarrental.nexGenCarRental.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.services.abstracts.BrandService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.AddBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.UpdateBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(ApiPathConstants.BRANDS_BASE_URL)
@AllArgsConstructor
public class BrandsController {
    private final BrandService brandService;

    @GetMapping(ApiPathConstants.GET_ALL_BRANDS)
    @ResponseStatus(HttpStatus.OK)
    public List<GetBrandListResponse> getAll() {
        return brandService.getAll();
    }

    @GetMapping(ApiPathConstants.GET_BRAND_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    public GetBrandResponse getById(int id) {
        return brandService.getById(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> add(
            @RequestPart("addBrandRequest") String addBrandRequestString,
            @RequestPart("logoFile") MultipartFile logoFile) throws JsonProcessingException {
            AddBrandRequest addBrandRequest = new ObjectMapper().readValue(addBrandRequestString, AddBrandRequest.class);
            brandService.customAdd(addBrandRequest, logoFile);
            return ResponseEntity.ok("Brand successfully added with logo.");
    }

    @PutMapping(value=ApiPathConstants.UPDATE_BRAND,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestPart("updateBrandRequest") @Valid String updateBrandRequestString,
                                    @RequestPart("logoFile") MultipartFile logoFile) throws JsonProcessingException {
            UpdateBrandRequest updateBrandRequest = new ObjectMapper().readValue(updateBrandRequestString, UpdateBrandRequest.class);
            brandService.customUpdate(updateBrandRequest, logoFile);
            return ResponseEntity.ok("Brand successfully updated with new logo.");
    }

    @DeleteMapping(ApiPathConstants.DELETE_BRAND)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        brandService.customDelete(id);
    }
}


