package com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.CustomerConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {

    @Positive(message = CustomerConstants.ID_POSITIVE)
    @NotNull(message = CustomerConstants.USER_ID)
    private int userId;
}
