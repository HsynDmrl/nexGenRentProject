package com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
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

    @Positive(message = ErrorConstants.ID_POSITIVE)
    @NotNull(message = ErrorConstants.EMPLOYEE_ID_NULL)
    private int id;

    @Min(value = 0, message =ErrorConstants.EMPLOYEE_MIN )
    @NotNull(message =ErrorConstants.EMPLOYEE_SALARY )
    private double salary;

    @Positive(message = ErrorConstants.ID_POSITIVE)
    @NotNull(message = ErrorConstants.USER_ID)
    private int userId;
}
