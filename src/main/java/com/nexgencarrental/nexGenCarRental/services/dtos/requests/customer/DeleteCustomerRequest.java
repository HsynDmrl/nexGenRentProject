package com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCustomerRequest {
    @Positive(message = "Id positive number!")
    private int id;
}
