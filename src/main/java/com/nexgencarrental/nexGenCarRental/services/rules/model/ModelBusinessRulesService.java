package com.nexgencarrental.nexGenCarRental.services.rules.model;


public interface ModelBusinessRulesService {
    void existsByName(String name);
    void deleteModel(int modelId);
}
