package com.nexgencarrental.nexGenCarRental.services.rules.user;

import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.UpdateUserRequest;

public interface UserBusinessRulesService {
    void checkAddUserRules(User user);
    void checkUpdateUserRules(UpdateUserRequest updateUserRequest);
    void checkDeleteUserRules(User deleteUserRequest);
}
