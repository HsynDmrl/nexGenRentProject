package com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {
    private String nationalityId;
    private int userId;
}
