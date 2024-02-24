package com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice.GetInvoiceResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalAdminResponse {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate returnDate;
    private double startKilometer;
    private Double endKilometer;
    private double totalPrice;
    private double discount;
    private GetModelResponse model;
    private GetCustomerResponse customer;
    private GetEmployeeResponse employee;
    private GetInvoiceResponse invoice;
}
