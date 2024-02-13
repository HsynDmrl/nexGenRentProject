package com.nexgencarrental.nexGenCarRental.services.rules.model;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Model;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.MODEL_NAME_ALREADY_EXISTS;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ENTITY_NOT_FOUND;

@Service
@AllArgsConstructor
public class ModelBusinessRulesManager implements ModelBusinessRulesService {
    private final ModelRepository modelRepository;

    @Override
    public void existsByName(String name) {
        if (modelRepository.existsByName(name.trim().replaceAll("\\s", ""))) {
            throw new ConflictException(MODEL_NAME_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteModel(int modelId) {
        Optional<Model> optionalModel = modelRepository.findById(modelId);
        if (optionalModel.isPresent()) {
            Model model = optionalModel.get();
            modelRepository.delete(model);
        } else {
            throw new DataNotFoundException(ENTITY_NOT_FOUND);
        }
    }
}
