package com.nexgencarrental.nexGenCarRental.services.dtos.requests.role;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoleRequest {

    @Size(min = 2,message ="Enter a role consisting of at least 2 letters")
    private String name;
}
