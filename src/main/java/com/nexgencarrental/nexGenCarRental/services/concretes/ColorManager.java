package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Color;
import com.nexgencarrental.nexGenCarRental.repositories.ColorRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ColorService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.AddColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.UpdateColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.color.ColorBusinessRulesService;
import org.springframework.stereotype.Service;

@Service
public class ColorManager extends BaseManager<Color, ColorRepository, GetColorResponse, GetColorListResponse,
        AddColorRequest, UpdateColorRequest> implements ColorService {
    private final ColorBusinessRulesService colorBusinessRulesService;
    public ColorManager(ColorRepository repository, ModelMapperService modelMapperService,
                        ColorBusinessRulesService colorBusinessRulesService) {
        super(repository, modelMapperService, GetColorResponse.class, GetColorListResponse.class, Color.class,
                AddColorRequest.class, UpdateColorRequest.class);
        this.colorBusinessRulesService = colorBusinessRulesService;
    }
    @Override
    public void customAdd(AddColorRequest addColorRequest) {
        colorBusinessRulesService.existsByName(addColorRequest.getName());
        add(addColorRequest, Color.class);
    }
    @Override
    public void customUpdate(UpdateColorRequest updateColorRequest) {
        colorBusinessRulesService.existsByName(updateColorRequest.getName());
        update(updateColorRequest, Color.class);
    }
}
