package com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {
    @Positive(message = ApplicationConstants.ID_POSITIVE)
    @NotNull(message = ApplicationConstants.USER_ID)
    private int userId;
}
