package com.nexgencarrental.nexGenCarRental.services.rules.rental;

import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.ErrorConstants;
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
            throw new RuntimeException(ErrorConstants.START_DATE_BEFORE_TODAY + LocalDate.now());
        }

        // Bitiş tarihi kontrolü
        if (addRentalRequest.getEndDate().isBefore(addRentalRequest.getStartDate())) {
            throw new RuntimeException(ErrorConstants.END_DATE_BEFORE_START_DATE + addRentalRequest.getStartDate());
        }

        // Kiralama süresi kontrolü
        if (ChronoUnit.DAYS.between(addRentalRequest.getStartDate(), addRentalRequest.getEndDate()) > 25
                || 0 == ChronoUnit.DAYS.between(addRentalRequest.getStartDate(), addRentalRequest.getEndDate())) {
            throw new RuntimeException(ErrorConstants.RENTAL_MIN_MAX_DAYS);
        }
    }
    public void validateUpdateRentalRequest(UpdateRentalRequest updateRentalRequest) {
        // Başlangıç tarihi kontrolü
        if(updateRentalRequest.getStartDate().isBefore(LocalDate.now())){
            throw new RuntimeException(ErrorConstants.UPDATED_START_DATE_BEFORE_TODAY + LocalDate.now());
        }

        // Bitiş tarihi kontrolü
        if (updateRentalRequest.getEndDate().isBefore(updateRentalRequest.getStartDate())){
            throw new RuntimeException(ErrorConstants.UPDATED_END_DATE_BEFORE_START_DATE + updateRentalRequest.getStartDate());
        }

        // Kiralama süresi kontrolü
        if (ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate())  > 25
                || 0 == ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate())) {
            throw new RuntimeException(ErrorConstants.UPDATED_RENTAL_MIN_MAX_DAYS);
        }
    }
}
