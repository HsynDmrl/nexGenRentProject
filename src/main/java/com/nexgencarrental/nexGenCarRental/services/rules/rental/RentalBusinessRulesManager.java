package com.nexgencarrental.nexGenCarRental.services.rules.rental;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.*;
import com.nexgencarrental.nexGenCarRental.repositories.*;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalAdminRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalAdminRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ENTITY_NOT_FOUND;

@Service
@AllArgsConstructor
public class RentalBusinessRulesManager implements RentalBusinessRulesService {

    private final CarRepository carRepository;
    private final InvoiceRepository invoiceRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;

    public void validateRentalDates(LocalDate startDate, LocalDate endDate) {
        // Başlangıç tarihi kontrolü
        if (startDate.isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ApplicationConstants.START_DATE_BEFORE_TODAY + LocalDate.now());
        }

        // Bitiş tarihi kontrolü
        if (endDate.isBefore(startDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ApplicationConstants.END_DATE_BEFORE_START_DATE + startDate);
        }

        // Kiralama süresi kontrolü
        if (ChronoUnit.DAYS.between(startDate, endDate) > 25
                || 0 == ChronoUnit.DAYS.between(startDate, endDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ApplicationConstants.RENTAL_MIN_MAX_DAYS);
        }
    }

    @Override
    public void validateAdminRentalRequest(AddRentalAdminRequest addRentalAdminRequest) {
        validateRentalDates(addRentalAdminRequest.getStartDate(), addRentalAdminRequest.getEndDate());

        if (addRentalAdminRequest.getEndKilometer() != null && addRentalAdminRequest.getEndKilometer() < addRentalAdminRequest.getStartKilometer()) {
            throw new IllegalArgumentException("End kilometer cannot be less than start kilometer");
        }
    }

    @Override
    public void validateAdminRentalRequest(UpdateRentalAdminRequest updateRentalAdminRequest) {

    }

    @Override
    public void validateAddRentalRequest(AddRentalRequest addRentalRequest) {
        validateRentalDates(addRentalRequest.getStartDate(), addRentalRequest.getEndDate());
    }

    public void validateUpdateRentalRequest(UpdateRentalRequest updateRentalRequest) {
        // Başlangıç tarihi kontrolü
        if (updateRentalRequest.getStartDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ApplicationConstants.UPDATED_START_DATE_BEFORE_TODAY + LocalDate.now());
        }

        // Bitiş tarihi kontrolü
        if (updateRentalRequest.getEndDate().isBefore(updateRentalRequest.getStartDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ApplicationConstants.UPDATED_END_DATE_BEFORE_START_DATE + updateRentalRequest.getStartDate());
        }

        // Kiralama süresi kontrolü
        if (ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate()) > 25
                || 0 == ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ApplicationConstants.UPDATED_RENTAL_MIN_MAX_DAYS);
        }
    }

    @Override
    public void validateDeleteRentalRequest(int rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new DataNotFoundException(ENTITY_NOT_FOUND));

        rentalRepository.delete(rental);

        Car car = rental.getCar();
        Customer customer = rental.getCustomer();
        Employee employee = rental.getEmployee();
        List<Invoice> invoices = rental.getInvoices();

        if (car != null) {
            car.getRentals().remove(rental);
            carRepository.save(car);
        }
        if (customer != null) {
            customer.getRentals().remove(rental);
            customerRepository.save(customer);
        }
        if (employee != null) {
            employee.getRentals().remove(rental);
            employeeRepository.save(employee);
        }
        if (invoices != null) {
            for (Invoice invoice : invoices) {
                invoice.setRental(null);
                invoiceRepository.save(invoice);
            }
        }
    }
}
