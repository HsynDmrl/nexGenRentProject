package com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalListResponse {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate returnDate;
    private double startKilometer;
    private Double endKilometer;
    private double totalPrice;
    private double discount;
    private String carModelBrandName;
    private String carModelName;
    private String customerName;
    private String employeeName;
}
