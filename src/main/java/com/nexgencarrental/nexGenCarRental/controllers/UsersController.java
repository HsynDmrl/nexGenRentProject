package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.services.JwtService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.services.abstracts.EmployeeService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RentalService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.LoginRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.AddEmployeeRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.AddUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.CreateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.UpdateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiPathConstants.USERS_BASE_URL)

@AllArgsConstructor
public class UsersController {
    private final UserService userService;
    @GetMapping(ApiPathConstants.GET_ALL_USERS)
    public List<GetUserListResponse> getAll(){
        return userService.getAll();
    }
    @GetMapping(ApiPathConstants.GET_USER_BY_ID)
    public GetUserResponse getById(int id){
        return userService.getById(id);
    }
    @PostMapping(ApiPathConstants.ADD_USER)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddUserRequest addUserRequest) {
        this.userService.add(addUserRequest, User.class);
    }

    @PutMapping(ApiPathConstants.UPDATE_USER)
    public void update(@RequestBody @Valid UpdateUserRequest updateUserRequest){
        userService.update(updateUserRequest, User.class);
    }

    @DeleteMapping(ApiPathConstants.DELETE_USER)
    public void delete(@PathVariable int id){
        userService.delete(id);
    }

    @GetMapping(ApiPathConstants.GET_USER_BY_EMAIL)
    public GetUserResponse getByEmail(@RequestParam String email){
        return userService.getByEmail(email);
    }
}
