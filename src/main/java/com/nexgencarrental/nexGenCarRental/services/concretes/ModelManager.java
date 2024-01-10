package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Model;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.BrandService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ModelService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.*;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.*;
import com.nexgencarrental.nexGenCarRental.services.rules.model.ModelBusinessRulesService;
import org.springframework.stereotype.Service;

@Service
public class ModelManager extends BaseManager<Model, ModelRepository, GetModelResponse, GetModelListResponse,
        AddModelRequest, UpdateModelRequest> implements ModelService {
    private final BrandService brandService;
    private final ModelBusinessRulesService modelBusinessRulesService;
    public ModelManager(ModelRepository repository, ModelMapperService modelMapperService,
                        BrandService brandService, ModelBusinessRulesService modelBusinessRulesService) {
        super(repository, modelMapperService, GetModelResponse.class, GetModelListResponse.class, Model.class,
                AddModelRequest.class, UpdateModelRequest.class);
        this.brandService = brandService;
        this.modelBusinessRulesService = modelBusinessRulesService;
    }
    @Override
    public void customAdd(AddModelRequest addModelRequest) {
        brandService.getById(addModelRequest.getBrandId()); // Brand id kontrolü
        modelBusinessRulesService.existsByName(addModelRequest.getName()); // ModelName kontrolü
        add(addModelRequest, Model.class);
    }
    @Override
    public void customUpdate(UpdateModelRequest updateModelRequest) {
        brandService.getById(updateModelRequest.getBrandId()); // Brand id kontrolü
        modelBusinessRulesService.existsByName(updateModelRequest.getName()); // ModelName kontrolü
        update(updateModelRequest, Model.class);
    }
}
