package com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.BrandConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
    @NotNull(message = BrandConstants.BRAND_ID_NULL)
    @Positive(message = BrandConstants.ID_POSITIVE)
    private int id;

    @Size(min = 2, message = BrandConstants.BRAND_NAME_SIZE)
    @Pattern(regexp = "^[A-Z][a-z]{1,13}$", message = BrandConstants.BRAND_NAME_PATTERN)
    @NotBlank(message = BrandConstants.BRAND_NAME_BLANK )
    private String name;

    @NotBlank(message = BrandConstants.BRAND_LOGO_BLANK)
    private String logoPath;
}
