package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.RoleRepository;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.*;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.*;
import com.nexgencarrental.nexGenCarRental.services.rules.user.UserBusinessRulesService;
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
    private final UserBusinessRulesService userBusinessRulesService;

    public UserManager(UserRepository repository, ModelMapperService modelMapperService, UserRepository userRepository, RoleRepository roleRepository, UserBusinessRulesService userBusinessRulesService) {
        super(repository, modelMapperService, GetUserResponse.class, GetUserListResponse.class, User.class,
                AddUserRequest.class, UpdateUserRequest.class);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userBusinessRulesService = userBusinessRulesService;
    }

    /*@Override
    public void add(User user) {
        //userBusinessRulesService.existsByNationalityId(user.getNationalityId()); // NationalityId kontrolü
        try {
            userRepository.save(user);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error during adding user", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error during adding user", ex);
        }
    }*/

    @Override
    public void customAdd(AddUserRequest addUserRequest) {
        userBusinessRulesService.existsByNationalityId(addUserRequest.getNationalityId()); // NationalityId kontrolü
        add(addUserRequest, User.class);
    }
    @Override
    public void customUpdate(UpdateUserRequest updateUserRequest) {
        userBusinessRulesService.existsByNationalityId(updateUserRequest.getNationalityId()); // NationalityId kontrolü
        update(updateUserRequest, User.class);
    }

    public GetUserResponse getByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email).orElse(null);
            return modelMapperService.forResponse().map(user, GetUserResponse.class);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error during getting user by email", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error during getting user by email", ex);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error during finding user by email", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error during finding user by email", ex);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try {
            return userRepository.existsByName(email);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error during checking if user exists by email", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error during checking if user exists by email", ex);
        }
    }

    @Override
    public Optional<Role> findRoleById(int roleId) {
        try {
            return roleRepository.findById(roleId);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error during finding role by id", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error during finding role by id", ex);
        }
    }

    @Override
    public Optional<User> findById(int userId) {
        try {
            return userRepository.findById(userId);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error during finding user by id", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error during finding user by id", ex);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user found!"));
        } catch (UsernameNotFoundException ex) {
            throw new RuntimeException("Error during loading user by username", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error during loading user by username", ex);
        }
    }
}