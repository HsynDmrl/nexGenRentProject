package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.RoleRepository;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.*;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.*;
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
        userRepository.save(user);
    }

    public GetUserResponse getByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return modelMapperService.forResponse().map(user, GetUserResponse.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByName(email);
    }

    @Override
    public Optional<Role> findRoleById(int roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user found!"));
    }
}