package com.nexgencarrental.nexGenCarRental.services.dtos.requests.model;

import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.ErrorConstants;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateModelRequest {
    @Positive(message = ErrorConstants.UPDATE_MODEL_ID_POSITIVE)
    private int id;

    @Size(min = 2, message = ErrorConstants.MODEL_NAME_SIZE)
    private String name;

    @Positive(message = ErrorConstants.ADD_MODEL_BRAND_ID_POSITIVE)
    private int brandId;
}
