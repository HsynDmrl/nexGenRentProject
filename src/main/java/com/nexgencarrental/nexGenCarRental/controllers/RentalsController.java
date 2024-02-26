package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RentalService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalAdminRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalAdminRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalAdminListResponse;
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
    @ResponseStatus(HttpStatus.OK)
    public List<GetRentalListResponse> getAll() {
        return rentalService.getAll();
    }

    @GetMapping(ApiPathConstants.GET_RENTAL_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    public GetRentalResponse getById(@PathVariable int id) {
        return rentalService.getById(id);
    }

    @GetMapping(ApiPathConstants.GET_ALL_ADMIN_RENTALS)
    @ResponseStatus(HttpStatus.OK)
    public List<GetRentalAdminListResponse> getAdminAll() {
        return rentalService.getAdminAll();
    }

    @GetMapping(ApiPathConstants.GET_RENTAL_ADMIN_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    public GetRentalResponse getByAdminId(@PathVariable int id) {
        return rentalService.getByAdminId(id);
    }


    @PostMapping(ApiPathConstants.ADD_RENTAL_ADMIN)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void adminAdd(@RequestBody @Valid AddRentalAdminRequest addRentalAdminRequest) {
        rentalService.rentalAdminAdd(addRentalAdminRequest);
    }

    @PutMapping(ApiPathConstants.UPDATE_RENTAL_ADMIN)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void adminUpdate(@RequestBody @Valid UpdateRentalAdminRequest updateRentalAdminRequest) {
        rentalService.rentalAdminUpdate(updateRentalAdminRequest);
    }

    @PostMapping(ApiPathConstants.ADD_RENTAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddRentalRequest addRentalRequest) {
        rentalService.customAdd(addRentalRequest);
    }

    @PutMapping(ApiPathConstants.UPDATE_RENTAL)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
        rentalService.customUpdate(updateRentalRequest);
    }

    @DeleteMapping(ApiPathConstants.DELETE_RENTAL)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        rentalService.customDelete(id);
    }
}
