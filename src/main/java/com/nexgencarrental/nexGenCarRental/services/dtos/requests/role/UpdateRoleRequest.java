package com.nexgencarrental.nexGenCarRental.services.dtos.requests.role;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleRequest {

    @Positive(message = ErrorConstants.UPDATE_ROLE_ID_POSITIVE)
    private int id;

    @Size(min = 2, message = ErrorConstants.ADD_ROLE_NAME_SIZE)
    private String name;
}
