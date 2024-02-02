package com.nexgencarrental.nexGenCarRental.controllers;

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
@RequestMapping("api/rentals")
public class RentalsController {
    private final RentalService rentalService;
    @GetMapping("/getAll")
    public List<GetRentalListResponse> getAll(){
        return rentalService.getAll();
    }
    @GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable int id){
        return rentalService.getById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddRentalRequest addRentalRequest) {
        rentalService.customAdd(addRentalRequest);
    }
    @PutMapping()
    public void update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest){
        rentalService.customUpdate(updateRentalRequest);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id ) {
        this.rentalService.delete(id);
    }

}
