package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RentalService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ApiPathConstants.RENTALS_BASE_URL)
public class RentalsController {
    private final RentalService rentalService;

    @GetMapping(ApiPathConstants.GET_ALL_RENTALS)
    public List<GetRentalListResponse> getAll() {
        return rentalService.getAll();
    }

    @GetMapping(ApiPathConstants.GET_RENTAL_BY_ID)
    public GetRentalResponse getById(@PathVariable int id) {
        return rentalService.getById(id);
    }

    @PostMapping(ApiPathConstants.ADD_RENTAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddRentalRequest addRentalRequest) {
        rentalService.customAdd(addRentalRequest);
    }

    @PutMapping(ApiPathConstants.UPDATE_RENTAL)
    public void update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
        rentalService.customUpdate(updateRentalRequest);
    }

    @DeleteMapping(ApiPathConstants.DELETE_RENTAL)
    public void delete(@PathVariable int id) {
        this.rentalService.delete(id);
    }

}
