package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Model;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.DeleteBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.DeleteCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.AddModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.DeleteModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.UpdateModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;

public interface ModelService extends BaseService<Model, ModelRepository, GetModelResponse,
        GetModelListResponse, AddModelRequest, UpdateModelRequest> {
    void customAdd(AddModelRequest addModelRequest);

    void customUpdate(UpdateModelRequest updateModelRequest);

    void customDelete(DeleteModelRequest deleteModelRequest);
}
