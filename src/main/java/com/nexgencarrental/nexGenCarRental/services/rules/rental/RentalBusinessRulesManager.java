package com.nexgencarrental.nexGenCarRental.services.rules.rental;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class RentalBusinessRulesManager implements RentalBusinessRulesService{
    public void validateAddRentalRequest(AddRentalRequest addRentalRequest) {
        // Başlangıç tarihi kontrolü
        if (addRentalRequest.getStartDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Start date cannot be earlier than today, which is " + LocalDate.now());
        }

        // Bitiş tarihi kontrolü
        if (addRentalRequest.getEndDate().isBefore(addRentalRequest.getStartDate())) {
            throw new RuntimeException("The entered date must be after " + addRentalRequest.getStartDate());
        }

        // Kiralama süresi kontrolü
        if (ChronoUnit.DAYS.between(addRentalRequest.getStartDate(), addRentalRequest.getEndDate()) > 25
                || 0 == ChronoUnit.DAYS.between(addRentalRequest.getStartDate(), addRentalRequest.getEndDate())) {
            throw new RuntimeException("A Car can be rented for a minimum of 1 day and a maximum of 25 days.");
        }
    }
    public void validateUpdateRentalRequest(UpdateRentalRequest updateRentalRequest) {
        // Başlangıç tarihi kontrolü
        if(updateRentalRequest.getStartDate().isBefore(LocalDate.now())){
            throw new RuntimeException("Start date for the updated rental cannot be earlier than today, which is " + LocalDate.now());
        }

        // Bitiş tarihi kontrolü
        if (updateRentalRequest.getEndDate().isBefore(updateRentalRequest.getStartDate())){
            throw new RuntimeException("The entered date for the updated rental must be after " + updateRentalRequest.getStartDate());
        }

        // Kiralama süresi kontrolü
        if (ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate())  > 25
                || 0 == ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate())) {
            throw new RuntimeException("The updated rental must be for a minimum of 1 day and a maximum of 25 days.");
        }
    }
}
