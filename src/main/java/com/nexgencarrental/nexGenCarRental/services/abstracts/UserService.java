package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.AddUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.UpdateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService extends BaseService<User, UserRepository, GetUserResponse,
        GetUserListResponse, AddUserRequest, UpdateUserRequest>, UserDetailsService {

    void add(User user);

    void customUpdate(UpdateUserRequest updateUserRequest);

    GetUserResponse getByEmail(String email);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Role> findRoleById(int roleId);

    Optional<User> findById(int userId);

    UserDetails loadUserByUsername(String username);
}