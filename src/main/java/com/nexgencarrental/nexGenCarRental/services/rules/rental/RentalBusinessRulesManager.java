package com.nexgencarrental.nexGenCarRental.services.rules.rental;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Customer;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Employee;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.repositories.CustomerRepository;
import com.nexgencarrental.nexGenCarRental.repositories.EmployeeRepository;
import com.nexgencarrental.nexGenCarRental.repositories.RentalRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ENTITY_NOT_FOUND;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ROLE_NOT_FOUND;

@Service
@AllArgsConstructor
public class RentalBusinessRulesManager implements RentalBusinessRulesService {

    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;

    public void validateAddRentalRequest(AddRentalRequest addRentalRequest) {
        // Başlangıç tarihi kontrolü
        if (addRentalRequest.getStartDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ApplicationConstants.START_DATE_BEFORE_TODAY + LocalDate.now());
        }

        // Bitiş tarihi kontrolü
        if (addRentalRequest.getEndDate().isBefore(addRentalRequest.getStartDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ApplicationConstants.END_DATE_BEFORE_START_DATE + addRentalRequest.getStartDate());
        }

        // Kiralama süresi kontrolü
        if (ChronoUnit.DAYS.between(addRentalRequest.getStartDate(), addRentalRequest.getEndDate()) > 25
                || 0 == ChronoUnit.DAYS.between(addRentalRequest.getStartDate(), addRentalRequest.getEndDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ApplicationConstants.RENTAL_MIN_MAX_DAYS);
        }
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

        // Rental varlığını sil
        rentalRepository.delete(rental);

        // Rental ile ilişkili Car, Customer ve Employee varlıklarını kontrol et
        Car car = rental.getCar();
        Customer customer = rental.getCustomer();
        Employee employee = rental.getEmployee();

        // Car, Customer ve Employee varlıklarının durumunu güncelle
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
    }
}
