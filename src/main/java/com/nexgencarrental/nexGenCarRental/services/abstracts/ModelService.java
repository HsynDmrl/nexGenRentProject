package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Model;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.AddBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.UpdateBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.AddModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.UpdateModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;

import java.util.List;

public interface ModelService extends BaseService<Model, ModelRepository, GetModelResponse,
        GetModelListResponse, AddModelRequest, UpdateModelRequest> {
    void customAdd(AddModelRequest addModelRequest);
    void customUpdate(UpdateModelRequest updateModelRequest);
}
