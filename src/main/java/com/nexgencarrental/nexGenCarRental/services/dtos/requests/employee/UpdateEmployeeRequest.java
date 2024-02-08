package com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.EmployeeConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {

    @Positive(message = EmployeeConstants.ID_POSITIVE)
    @NotNull(message = EmployeeConstants.EMPLOYEE_ID_NULL)
    private int id;

    @Min(value = 0, message =EmployeeConstants.EMPLOYEE_MIN )
    @NotNull(message =EmployeeConstants.EMPLOYEE_SALARY )
    private double salary;

    @Positive(message = EmployeeConstants.ID_POSITIVE)
    @NotNull(message = EmployeeConstants.USER_ID)
    private int userId;
}
