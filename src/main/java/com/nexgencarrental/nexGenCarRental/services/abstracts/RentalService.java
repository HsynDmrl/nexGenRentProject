package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.RentalRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalResponse;

import java.util.List;

public interface RentalService extends BaseService<Rental, RentalRepository, GetRentalResponse,
        GetRentalListResponse, AddRentalRequest, UpdateRentalRequest> {
    void customAdd(AddRentalRequest addRentalRequest);
    void customUpdate(UpdateRentalRequest updateRentalRequest);
}
