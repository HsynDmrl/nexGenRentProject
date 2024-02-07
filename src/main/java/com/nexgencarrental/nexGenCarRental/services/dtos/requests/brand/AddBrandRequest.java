package com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBrandRequest {
    @Size(min = 2, message = ApplicationConstants.BRAND_NAME_SIZE)
    @Pattern(regexp = "^[A-Z][a-z]{1,13}$", message = ApplicationConstants.BRAND_NAME_PATTERN)
    @NotBlank(message = ApplicationConstants.BRAND_NAME_BLANK )
    private String name;

    @NotBlank(message = ApplicationConstants.BRAND_LOGO_BLANK)
    private String logoPath;
}
