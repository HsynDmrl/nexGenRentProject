package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.ErrorConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.RoleRepository;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.*;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.*;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManager extends BaseManager<User, UserRepository, GetUserResponse, GetUserListResponse,
        AddUserRequest, UpdateUserRequest> implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserManager(UserRepository repository, ModelMapperService modelMapperService, UserRepository userRepository, RoleRepository roleRepository) {
        super(repository, modelMapperService, GetUserResponse.class, GetUserListResponse.class, User.class,
                AddUserRequest.class, UpdateUserRequest.class);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void add(User user) {
        try {
            userRepository.save(user);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ErrorConstants.ERROR_ADDING_USER, ex);
        } catch (Exception ex) {
            throw new RuntimeException(ErrorConstants.UNEXPECTED_ERROR_ADDING_USER, ex);
        }
    }

    public GetUserResponse getByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email).orElse(null);
            return modelMapperService.forResponse().map(user, GetUserResponse.class);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ErrorConstants.ERROR_GETTING_USER_BY_EMAIL, ex);
        } catch (Exception ex) {
            throw new RuntimeException(ErrorConstants.UNEXPECTED_ERROR_GETTING_USER_BY_EMAIL, ex);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ErrorConstants.ERROR_FINDING_USER_BY_EMAIL, ex);
        } catch (Exception ex) {
            throw new RuntimeException(ErrorConstants.UNEXPECTED_ERROR_FINDING_USER_BY_EMAIL, ex);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try {
            return userRepository.existsByName(email);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ErrorConstants.ERROR_CHECKING_USER_EXISTS_BY_EMAIL, ex);
        } catch (Exception ex) {
            throw new RuntimeException(ErrorConstants.UNEXPECTED_ERROR_CHECKING_USER_EXISTS_BY_EMAIL, ex);
        }
    }

    @Override
    public Optional<Role> findRoleById(int roleId) {
        try {
            return roleRepository.findById(roleId);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ErrorConstants.ERROR_FINDING_ROLE_BY_ID, ex);
        } catch (Exception ex) {
            throw new RuntimeException(ErrorConstants.UNEXPECTED_ERROR_FINDING_ROLE_BY_ID, ex);
        }
    }

    @Override
    public Optional<User> findById(int userId) {
        try {
            return userRepository.findById(userId);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ErrorConstants.ERROR_FINDING_USER_BY_ID, ex);
        } catch (Exception ex) {
            throw new RuntimeException(ErrorConstants.UNEXPECTED_ERROR_FINDING_USER_BY_ID, ex);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(ErrorConstants.NO_USER_FOUND));
        } catch (UsernameNotFoundException ex) {
            throw new RuntimeException(ErrorConstants.ERROR_LOADING_USER_BY_USERNAME, ex);
        } catch (Exception ex) {
            throw new RuntimeException(ErrorConstants.UNEXPECTED_ERROR_LOADING_USER_BY_USERNAME, ex);
        }
    }
}