package com.nexgencarrental.nexGenCarRental.services.dtos.requests.color;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
    @Positive(message = ErrorConstants.UPDATE_COLOR_ID_POSITIVE)
    private int id;

    @Size(min = 2, message = ErrorConstants.COLOR_NAME_SIZE)
    @Pattern(regexp = "^[A-Z][a-z]{1,13}$", message = ErrorConstants.COLOR_NAME_PATTERN)
    private String name;
}
