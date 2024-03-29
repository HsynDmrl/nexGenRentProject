package com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.InvoiceConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.RentalConstants;
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
public class AddRentalAdminRequest {

    @NotNull(message = RentalConstants.START_DATE_NULL)
    @FutureOrPresent(message = RentalConstants.START_DATE_FUTURE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = RentalConstants.END_DATE_NULL)
    @Future(message = RentalConstants.END_DATE_FUTURE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private double startKilometer;

    private Double endKilometer;

    private double totalPrice;

    private double discount;

    @Positive(message = RentalConstants.COLOR_ID_POSITIVE)
    @NotNull(message = RentalConstants.COLOR_ID_NULL)
    private int carId;

    @Positive(message = RentalConstants.CUSTOMER_ID_POSITIVE)
    @NotNull(message = RentalConstants.CUSTOMER_ID_NULL)
    private int customerId;

    @Positive(message = RentalConstants.EMPLOYEE_ID_POSITIVE)
    @NotNull(message = RentalConstants.EMPLOYEE_ID_NULL)
    private int employeeId;

    @Positive(message = InvoiceConstants.ID_POSITIVE)
    @NotNull(message = InvoiceConstants.INVOICE_ID_NULL)
    private int invoicesId;
}
