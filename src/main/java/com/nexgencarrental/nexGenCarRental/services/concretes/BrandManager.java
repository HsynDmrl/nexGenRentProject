package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Brand;
import com.nexgencarrental.nexGenCarRental.repositories.BrandRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.BrandService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.AddBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.UpdateBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.brand.BrandBusinessRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@Service
public class BrandManager extends BaseManager<Brand, BrandRepository, GetBrandResponse, GetBrandListResponse,
        AddBrandRequest, UpdateBrandRequest> implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandBusinessRulesService brandBusinessRulesService;
    private final Cloudinary cloudinaryService; // CloudinaryService enjekte edildi


    @Autowired
    public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService,
                        BrandBusinessRulesService brandBusinessRulesService, Cloudinary cloudinaryService) {
        super(brandRepository, modelMapperService, GetBrandResponse.class, GetBrandListResponse.class, Brand.class,
                AddBrandRequest.class, UpdateBrandRequest.class);
        this.brandBusinessRulesService = brandBusinessRulesService;
        this.cloudinaryService = cloudinaryService;
        this.brandRepository = brandRepository; // BrandRepository nesnesini atayın
    }
    @Override
    public void customAdd(AddBrandRequest addBrandRequest, MultipartFile logoFile) {
        brandBusinessRulesService.checkIfBrandNameExists(addBrandRequest.getName());

        String logoUrl = null;
        try {
            // Cloudinary üzerinden dosya yükleme
            Map uploadResult = cloudinaryService.uploader().upload(logoFile.getBytes(), ObjectUtils.emptyMap());
            logoUrl = (String) uploadResult.get("url");
        } catch (IOException e) {
            // Hata yönetimi
            e.printStackTrace();
        }

        Brand brand = new Brand();
        brand.setName(addBrandRequest.getName());
        brand.setLogoPath(logoUrl);

        brandRepository.save(brand);
    }

    @Override
    public void customUpdate(UpdateBrandRequest updateBrandRequest, MultipartFile logoFile) {
        brandBusinessRulesService.checkIfBrandNameExistsOnUpdate(updateBrandRequest.getName(), updateBrandRequest.getId());

        Brand brand = brandRepository.findById(updateBrandRequest.getId())
                .orElseThrow(() -> new RuntimeException("Brand not found with ID: " + updateBrandRequest.getId()));

        String logoUrl = null;
        try {
            // Cloudinary üzerinden dosya yükleme
            Map uploadResult = cloudinaryService.uploader().upload(logoFile.getBytes(), ObjectUtils.emptyMap());
            logoUrl = (String) uploadResult.get("url");
        } catch (IOException e) {
            // Hata yönetimi
            e.printStackTrace();
        }

        brand.setName(updateBrandRequest.getName());
        brand.setLogoPath(logoUrl);

        brandRepository.save(brand);
    }

    @Override
    public void customDelete(int id) {
        brandBusinessRulesService.deleteBrandWithModels(id);
    }

    @Override
    public GetBrandFilterResponse convertToGetBrandFilterResponse(Brand brand) {
        GetBrandFilterResponse response = new GetBrandFilterResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());
        response.setLogoPath(brand.getLogoPath());
        return response;
    }
}
