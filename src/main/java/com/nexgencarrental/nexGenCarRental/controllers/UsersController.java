package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.services.abstracts.EmployeeService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.AddEmployeeRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.AddUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.UpdateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin
public class UsersController {
    private final UserService userService;
    @GetMapping("/getAll")
    public List<GetUserListResponse> getAll(){
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public GetUserResponse getById(int id){
        return userService.getById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddUserRequest addUserRequest) {
        this.userService.add(addUserRequest, User.class);
    }

    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateUserRequest updateUserRequest){
        userService.update(updateUserRequest, User.class);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        userService.delete(id);
    }
}
