package com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

    @Positive (message = ApplicationConstants.UPDATE_RENTAL_ID_POSITIVE)
    @NotNull(message = ApplicationConstants.RENTAL_ID_NULL)
    private int id;

    @NotNull(message = ApplicationConstants.START_DATE_NULL)
    @FutureOrPresent(message = ApplicationConstants.START_DATE_FUTURE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message =ApplicationConstants.END_DATE_NULL )
    @Future(message = ApplicationConstants.END_DATE_FUTURE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Positive(message = ApplicationConstants.COLOR_ID_POSITIVE)
    @NotNull(message =  ApplicationConstants.COLOR_ID_NULL)
    private int carId;

    @Positive(message = ApplicationConstants.CUSTOMER_ID_POSITIVE)
    @NotNull(message = ApplicationConstants.CUSTOMER_ID_NULL)
    private int customerId;

    @Positive(message = ApplicationConstants.EMPLOYEE_ID_POSITIVE)
    @NotNull(message = ApplicationConstants.EMPLOYEE_ID_NULL)
    private int employeeId;
}
