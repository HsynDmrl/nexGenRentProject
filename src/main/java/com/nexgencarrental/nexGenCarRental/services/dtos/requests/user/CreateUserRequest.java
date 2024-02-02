package com.nexgencarrental.nexGenCarRental.services.dtos.requests.user;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {
    private String email;
    private String password;
    private int roleId;
}