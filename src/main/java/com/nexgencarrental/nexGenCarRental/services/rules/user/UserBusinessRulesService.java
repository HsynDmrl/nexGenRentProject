package com.nexgencarrental.nexGenCarRental.services.rules.user;

public interface UserBusinessRulesService {
    void existsByName(String name);

    void existsByNationalityId(String name);
}
