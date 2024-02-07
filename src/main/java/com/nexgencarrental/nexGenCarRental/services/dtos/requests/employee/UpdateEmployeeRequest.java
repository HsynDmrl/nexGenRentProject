package com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
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

    @Positive(message = ApplicationConstants.ID_POSITIVE)
    @NotNull(message = ApplicationConstants.EMPLOYEE_ID_NULL)
    private int id;

    @Min(value = 0, message =ApplicationConstants.EMPLOYEE_MIN )
    @NotNull(message =ApplicationConstants.EMPLOYEE_SALARY )
    private double salary;

    @Positive(message = ApplicationConstants.ID_POSITIVE)
    @NotNull(message = ApplicationConstants.USER_ID)
    private int userId;
}
