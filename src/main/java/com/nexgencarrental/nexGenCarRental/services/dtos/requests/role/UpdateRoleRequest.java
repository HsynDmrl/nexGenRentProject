package com.nexgencarrental.nexGenCarRental.services.dtos.requests.role;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleRequest {

    @Positive(message = "Id field cannot be less than 0.")
    private int id;

    @Size(min = 2,message = "Enter a role consisting of at least 2 letters" )
    private String name;
}
