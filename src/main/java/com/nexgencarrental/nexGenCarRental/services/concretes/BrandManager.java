package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Brand;
import com.nexgencarrental.nexGenCarRental.repositories.BrandRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.BrandService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.AddBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.DeleteBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.UpdateBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.brand.BrandBusinessRulesService;
import org.springframework.stereotype.Service;


@Service
public class BrandManager extends BaseManager<Brand, BrandRepository, GetBrandResponse, GetBrandListResponse,
        AddBrandRequest, UpdateBrandRequest> implements BrandService {
    private final BrandBusinessRulesService brandBusinessRulesService;


    public BrandManager(BrandRepository repository, ModelMapperService modelMapperService,
                        BrandBusinessRulesService brandBusinessRulesService) {
        super(repository, modelMapperService, GetBrandResponse.class, GetBrandListResponse.class, Brand.class,
                AddBrandRequest.class, UpdateBrandRequest.class);
        this.brandBusinessRulesService = brandBusinessRulesService;
    }

    @Override
    public void customAdd(AddBrandRequest addBrandRequest) {
        brandBusinessRulesService.checkIfBrandNameExists(addBrandRequest.getName());
        add(addBrandRequest, Brand.class);
    }

    @Override
    public void customUpdate(UpdateBrandRequest updateBrandRequest) {
        brandBusinessRulesService.checkIfBrandNameExistsOnUpdate(updateBrandRequest.getName(), updateBrandRequest.getId());
        update(updateBrandRequest, Brand.class);
    }

    @Override
    public void customDelete(DeleteBrandRequest deleteBrandRequest) {
        brandBusinessRulesService.deleteBrandWithModels(deleteBrandRequest.getId());
    }
}
