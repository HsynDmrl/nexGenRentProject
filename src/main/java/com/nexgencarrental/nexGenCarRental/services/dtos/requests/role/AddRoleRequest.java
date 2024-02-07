package com.nexgencarrental.nexGenCarRental.services.dtos.requests.role;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoleRequest {

    @Size(min = 2, message = ApplicationConstants.ADD_ROLE_NAME_SIZE)
    @NotBlank(message = ApplicationConstants.ROLE_NAME_BLANK)
    @Pattern(regexp = "^[A-Z][a-z]$",message = ApplicationConstants.ROLE_NAME_PATTERN)
    private String name;
}
