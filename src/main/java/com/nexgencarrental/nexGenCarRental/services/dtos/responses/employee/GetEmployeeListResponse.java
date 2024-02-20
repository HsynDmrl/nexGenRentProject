package com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeListResponse {
    private int id;

    private double salary;
}
