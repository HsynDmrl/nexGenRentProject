package com.nexgencarrental.nexGenCarRental.services.rules.rental;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalAdminRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalAdminRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;

public interface RentalBusinessRulesService {
    void validateAddRentalRequest(AddRentalRequest addRentalRequest);

    void validateAdminRentalRequest(AddRentalAdminRequest addRentalAdminRequest);

    void validateAdminRentalRequest(UpdateRentalAdminRequest updateRentalAdminRequest);

    void validateUpdateRentalRequest(UpdateRentalRequest updateRentalRequest);

    void validateDeleteRentalRequest(int rentalId);
}
