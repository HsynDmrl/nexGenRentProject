package com.nexgencarrental.nexGenCarRental.services.dtos.requests.role;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleRequest {

    @Positive(message = ApplicationConstants.UPDATE_ROLE_ID_POSITIVE)
    @NotNull(message = ApplicationConstants.ROLE_ID_NULL)
    private int id;

    @Size(min = 2, message = ApplicationConstants.ADD_ROLE_NAME_SIZE)
    @NotBlank(message = ApplicationConstants.ROLE_NAME_BLANK)
    @Pattern(regexp = "^[A-Z][a-z]$",message = ApplicationConstants.ROLE_NAME_PATTERN)
    private String name;
}
