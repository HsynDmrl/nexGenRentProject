package com.nexgencarrental.nexGenCarRental.services.dtos.requests.color;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.ColorConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
    @Positive(message = ColorConstants.UPDATE_COLOR_ID_POSITIVE)
    @NotNull(message = ColorConstants.COLOR_ID_NULL)
    private int id;

    @Size(min = 2, message = ColorConstants.COLOR_NAME_SIZE)
    @Pattern(regexp = "^[A-ZÇĞİÖŞÜ][a-zçğıöşü]{1,13}$", message = ColorConstants.COLOR_NAME_PATTERN)
    @NotBlank(message = ColorConstants.COLOR_NOT_BLANK)
    private String name;
}
