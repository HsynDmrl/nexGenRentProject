package com.nexgencarrental.nexGenCarRental.services.rules.rental;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class RentalBusinessRulesManager implements RentalBusinessRulesService {
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
}
