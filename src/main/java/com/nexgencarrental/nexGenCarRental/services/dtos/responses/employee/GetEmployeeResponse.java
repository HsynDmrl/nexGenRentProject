package com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.base.GetBaseResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeResponse extends GetBaseResponse {
    private double salary;

    private GetUserResponse user;
}
