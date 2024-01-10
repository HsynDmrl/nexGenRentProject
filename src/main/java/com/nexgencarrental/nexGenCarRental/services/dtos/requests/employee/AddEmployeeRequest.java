package com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeRequest {
    private double salary;
    private int userId;
}
