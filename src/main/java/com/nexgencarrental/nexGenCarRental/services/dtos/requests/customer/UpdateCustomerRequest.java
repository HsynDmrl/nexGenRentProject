package com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    private int id;

    private String nationalityId;

    private int userId;
}
