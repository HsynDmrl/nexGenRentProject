package com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
    @Positive (message = ErrorConstants.UPDATE_RENTAL_ID_POSITIVE)
    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    //private double discount;

    private int carId;

    private int customerId;

    private int employeeId;
}
