package com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRentalRequest {
    private LocalDate startDate;

    private LocalDate endDate;


/*  private LocalDate returnDate;

    private double startKilometer;

    private double endKilometer;

    private double discount;*/

    private int carId;

    private int customerId;

    private int employeeId;

}
