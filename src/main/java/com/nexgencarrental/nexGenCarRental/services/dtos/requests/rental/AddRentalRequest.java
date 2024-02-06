package com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRentalRequest {

    @NotNull(message = ErrorConstants.START_DATE_NULL)
    @FutureOrPresent(message = ErrorConstants.START_DATE_FUTURE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message =ErrorConstants.END_DATE_NULL )
    @Future(message = ErrorConstants.END_DATE_FUTURE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Positive(message = ErrorConstants.COLOR_ID_POSITIVE)
    @NotNull(message =  ErrorConstants.COLOR_ID_NULL)
    private int carId;

    @Positive(message = ErrorConstants.CUSTOMER_ID_POSITIVE)
    @NotNull(message = ErrorConstants.CUSTOMER_ID_NULL)
    private int customerId;

    @Positive(message = ErrorConstants.EMPLOYEE_ID_POSITIVE)
    @NotNull(message = ErrorConstants.EMPLOYEE_ID_NULL)
    private int employeeId;

}
