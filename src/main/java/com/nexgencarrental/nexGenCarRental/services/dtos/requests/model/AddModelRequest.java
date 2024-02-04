package com.nexgencarrental.nexGenCarRental.services.dtos.requests.model;

import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.ErrorConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddModelRequest {
    @Size(min = 2, message = ErrorConstants.MODEL_NAME_SIZE)
    @Pattern(regexp = "^[A-Z][a-z0-9]{0,13}$", message = ErrorConstants.MODEL_NAME_PATTERN)
    private String name;

    @Positive(message = ErrorConstants.ADD_MODEL_BRAND_ID_POSITIVE)
    private int brandId;
}
