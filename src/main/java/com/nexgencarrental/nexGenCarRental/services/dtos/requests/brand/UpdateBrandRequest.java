package com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
    @NotNull(message = ApplicationConstants.BRAND_ID_NULL)
    @Positive(message = ApplicationConstants.ID_POSITIVE)
    private int id;

    @Size(min = 2, message = ApplicationConstants.BRAND_NAME_SIZE)
    @Pattern(regexp = "^[A-Z][a-z]{1,13}$", message = ApplicationConstants.BRAND_NAME_PATTERN)
    @NotBlank(message = ApplicationConstants.BRAND_NAME_BLANK )
    private String name;

    @NotBlank(message = ApplicationConstants.BRAND_LOGO_BLANK)
    private String logoPath;
}
