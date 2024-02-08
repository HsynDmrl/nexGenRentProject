package com.nexgencarrental.nexGenCarRental.services.dtos.requests.model;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.ModelConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateModelRequest {

    @Positive(message = ModelConstants.UPDATE_MODEL_ID_POSITIVE)
    @NotNull(message = ModelConstants.MODEL_ID_NULL)
    private int id;

    @Size(min = 2, message = ModelConstants.MODELS_NAME_SIZE)
    @Pattern(regexp = "^[A-Z][a-z0-9]{0,13}$", message = ModelConstants.MODEL_NAME_PATTERN)
    @NotBlank(message = ModelConstants.MODEL_NAME_BLANK)
    private String name;

    @Positive(message = ModelConstants.ADD_MODEL_BRAND_ID_POSITIVE)
    @NotNull(message = ModelConstants.BRAND_ID_NULL)
    private int brandId;
}
