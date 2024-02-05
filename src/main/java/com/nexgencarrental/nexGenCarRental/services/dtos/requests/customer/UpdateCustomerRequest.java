package com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    @Positive(message = ErrorConstants.ID_POSITIVE)
    @NotNull(message = ErrorConstants.CUSTOMER_ID_NULL)
    private int id;

    @Positive(message = ErrorConstants.ID_POSITIVE)
    @NotNull(message = ErrorConstants.USER_ID)
    private int userId;
}
