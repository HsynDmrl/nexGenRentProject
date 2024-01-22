package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.*;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager extends BaseManager<User, UserRepository, GetUserResponse, GetUserListResponse,
        AddUserRequest, UpdateUserRequest> implements UserService {
    public UserManager(UserRepository repository, ModelMapperService modelMapperService) {
        super(repository, modelMapperService, GetUserResponse.class, GetUserListResponse.class, User.class,
                AddUserRequest.class, UpdateUserRequest.class);
    }
}