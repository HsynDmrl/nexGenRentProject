package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Color;
import com.nexgencarrental.nexGenCarRental.repositories.ColorRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.AddColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.UpdateColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;

public interface ColorService extends BaseService<Color, ColorRepository, GetColorResponse,
        GetColorListResponse, AddColorRequest, UpdateColorRequest> {
    void customAdd(AddColorRequest addColorRequest);

    void customUpdate(UpdateColorRequest updateColorRequest);

    void customDelete(int id);

    GetColorFilterResponse convertToGetColorFilterResponse(Color color);
}
