package com.nexgencarrental.nexGenCarRental.services.dtos.requests.model;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddModelRequest {

    @Size(min = 2, message = ApplicationConstants.MODELS_NAME_SIZE)
    @Pattern(regexp = "^[A-Z][a-z0-9]{0,13}$", message = ApplicationConstants.MODEL_NAME_PATTERN)
    @NotBlank(message = ApplicationConstants.MODEL_NAME_BLANK)
    private String name;

    @Positive(message = ApplicationConstants.ADD_MODEL_BRAND_ID_POSITIVE)
    @NotNull(message = ApplicationConstants.BRAND_ID_NULL)
    private int brandId;
}
