package com.nexgencarrental.nexGenCarRental.services.dtos.requests.color;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
    @Positive(message = ApplicationConstants.UPDATE_COLOR_ID_POSITIVE)
    @NotNull(message =ApplicationConstants.COLOR_ID_NULL)
    private int id;

    @Size(min = 2, message = ApplicationConstants.COLOR_NAME_SIZE)
    @Pattern(regexp = "^[A-Z][a-z]{1,13}$", message = ApplicationConstants.COLOR_NAME_PATTERN)
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
