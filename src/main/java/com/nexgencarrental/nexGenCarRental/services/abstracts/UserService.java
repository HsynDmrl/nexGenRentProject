package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.AddUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.UpdateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends BaseService<User, UserRepository, GetUserResponse,
        GetUserListResponse, AddUserRequest, UpdateUserRequest> , UserDetailsService {

    GetUserResponse getByEmail(String email);
    void add(User user);
}