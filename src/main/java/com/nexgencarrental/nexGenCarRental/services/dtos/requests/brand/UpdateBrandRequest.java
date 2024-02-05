package com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand;

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
public class UpdateBrandRequest {
    @Positive(message = ErrorConstants.ID_POSITIVE)
    private int id;

    @Size(min = 2, message = ErrorConstants.BRAND_NAME_SIZE)
    @Pattern(regexp = "^[A-Z][a-z]{1,13}$", message = ErrorConstants.BRAND_NAME_PATTERN)
    private String name;

    private String logoPath;
}
