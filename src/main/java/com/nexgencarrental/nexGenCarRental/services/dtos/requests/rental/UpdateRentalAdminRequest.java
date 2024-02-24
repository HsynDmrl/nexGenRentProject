package com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.RentalConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.UserConstants;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Customer;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Employee;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Invoice;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalAdminRequest {
    @Positive(message = RentalConstants.UPDATE_RENTAL_ID_POSITIVE)
    @NotNull(message = RentalConstants.RENTAL_ID_NULL)
    private int id;

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

    private int invoicesId;
}