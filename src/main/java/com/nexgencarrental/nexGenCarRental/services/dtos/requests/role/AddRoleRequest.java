package com.nexgencarrental.nexGenCarRental.services.dtos.requests.role;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoleRequest {

    @Size(min = 2, message = ErrorConstants.ADD_ROLE_NAME_SIZE)
    private String name;
}
