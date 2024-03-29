package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ModelService;
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
@RequestMapping(ApiPathConstants.MODELS_BASE_URL)
@AllArgsConstructor
public class ModelsController {
    private final ModelService modelService;

    @GetMapping(ApiPathConstants.GET_ALL_MODELS)
    @ResponseStatus(HttpStatus.OK)
    public List<GetModelListResponse> getAll() {
        return modelService.getAll();
    }

    @GetMapping(ApiPathConstants.GET_MODEL_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    public GetModelResponse getById(int id) {
        return modelService.getById(id);
    }

    @PostMapping(ApiPathConstants.ADD_MODEL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddModelRequest addModelRequest) {
        this.modelService.customAdd(addModelRequest);
    }

    @PutMapping(ApiPathConstants.UPDATE_MODEL)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid UpdateModelRequest updateModelRequest) {
        modelService.customUpdate(updateModelRequest);
    }

    @DeleteMapping(ApiPathConstants.DELETE_MODEL)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        modelService.customDelete(id);
    }
}
