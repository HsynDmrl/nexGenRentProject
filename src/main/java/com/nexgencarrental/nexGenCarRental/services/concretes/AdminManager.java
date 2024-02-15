package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.RoleRepository;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.NO_ROLE_ALREADY_EXISTS;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ROLE_NOT_FOUND;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class AdminManager implements AdminService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void updateUserRole(int userId, int roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException(USER_NOT_FOUND));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new DataNotFoundException(ROLE_NOT_FOUND));

        if (user.getRole().getId() == roleId) {
            throw new ConflictException(NO_ROLE_ALREADY_EXISTS);
        }

        user.setRole(role);
        userRepository.save(user);
    }
}
