package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Brand;
import com.nexgencarrental.nexGenCarRental.repositories.BrandRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.AddBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.UpdateBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandResponse;
import org.springframework.web.multipart.MultipartFile;

public interface BrandService extends BaseService<Brand, BrandRepository, GetBrandResponse,
        GetBrandListResponse, AddBrandRequest, UpdateBrandRequest> {
    void customAdd(AddBrandRequest addBrandRequest, MultipartFile logoFile);

    void customUpdate(UpdateBrandRequest updateBrandRequest, MultipartFile logoFile);

    void customDelete(int id);

    GetBrandFilterResponse convertToGetBrandFilterResponse(Brand brand);
}
