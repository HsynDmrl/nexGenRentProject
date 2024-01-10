package com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
    @Positive (message = "Id cannot be less than 0")
    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    //private double discount;

    private int carId;

    private int customerId;

    private int employeeId;
}
