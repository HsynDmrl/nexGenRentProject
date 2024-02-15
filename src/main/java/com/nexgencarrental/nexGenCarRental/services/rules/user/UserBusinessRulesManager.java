package com.nexgencarrental.nexGenCarRental.services.rules.user;


import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Customer;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Employee;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.UpdateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.DATA_CONFLICT;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.USER_NOT_FOUND;


@Service
@AllArgsConstructor
public class UserBusinessRulesManager implements UserBusinessRulesService {

    private UserRepository userRepository;

    @Override
    public void checkUpdateUserRules(UpdateUserRequest updateUserRequest) {
        User existingUser = userRepository.findById(updateUserRequest.getId()).orElse(null);
        if (existingUser == null) {
            throw new DataNotFoundException(USER_NOT_FOUND);
        }


        if (!existingUser.getNationalityId().equals(updateUserRequest.getNationalityId())) {
            if (userRepository.existsByNationalityId(updateUserRequest.getNationalityId())) {
                throw new ConflictException(DATA_CONFLICT);
            }
        }
    }

    @Override
    public void checkDeleteUserRules(User userToDelete) {
        for (Customer customer : userToDelete.getCustomers()) {
            customer.setUser(null);
        }
        for (Employee employee : userToDelete.getEmployees()) {
            employee.setUser(null);
        }
        userRepository.save(userToDelete);
        userRepository.delete(userToDelete);
    }
}
