package com.nexgencarrental.nexGenCarRental.services.dtos.requests.color;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.ColorConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddColorRequest {
    @Size(min = 2, message = ColorConstants.COLOR_NAME_SIZE)
    @Pattern(regexp = "^[A-Z][a-z]{1,13}$", message = ColorConstants.COLOR_NAME_PATTERN)
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
