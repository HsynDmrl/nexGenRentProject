package com.nexgencarrental.nexGenCarRental.services.dtos.requests.model;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateModelRequest {

    @Positive(message = ErrorConstants.UPDATE_MODEL_ID_POSITIVE)
    @NotNull(message = ErrorConstants.MODEL_ID_NULL)
    private int id;

    @Size(min = 2, message = ErrorConstants.MODEL_NAME_SIZE)
    @Pattern(regexp = "^[A-Z][a-z0-9]{0,13}$", message = ErrorConstants.MODEL_NAME_PATTERN)
    @NotBlank(message = ErrorConstants.MODEL_NAME_BLANK)
    private String name;

    @Positive(message = ErrorConstants.ADD_MODEL_BRAND_ID_POSITIVE)
    @NotNull(message = ErrorConstants.BRAND_ID_NULL)
    private int brandId;
}
