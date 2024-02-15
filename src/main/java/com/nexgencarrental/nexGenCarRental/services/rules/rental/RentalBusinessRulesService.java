package com.nexgencarrental.nexGenCarRental.services.rules.rental;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;

public interface RentalBusinessRulesService {
    void validateAddRentalRequest(AddRentalRequest addRentalRequest);

    void validateUpdateRentalRequest(UpdateRentalRequest updateRentalRequest);

    void validateDeleteRentalRequest(int rentalId);
}
