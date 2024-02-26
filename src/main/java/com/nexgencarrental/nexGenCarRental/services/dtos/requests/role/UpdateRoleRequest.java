package com.nexgencarrental.nexGenCarRental.services.dtos.requests.role;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.RoleConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleRequest {

    @Positive(message = RoleConstants.UPDATE_ROLE_ID_POSITIVE)
    @NotNull(message = RoleConstants.ROLE_ID_NULL)
    private int id;

    @Size(min = 2, message = RoleConstants.ADD_ROLE_NAME_SIZE)
    @NotBlank(message = RoleConstants.ROLE_NAME_BLANK)
    @Pattern(regexp = "^[A-Z]+$", message = RoleConstants.ROLE_NAME_PATTERN)
    private String name;
}
