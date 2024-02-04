package com.nexgencarrental.nexGenCarRental.controllers;
import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CustomerService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.AddCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.UpdateCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(ApiPathConstants.CUSTOMERS_BASE_URL)
@AllArgsConstructor
public class CustomersController {
    private final CustomerService customerService;
    @GetMapping(ApiPathConstants.GET_ALL_CUSTOMERS)
    public List<GetCustomerListResponse> getAll(){
        return customerService.getAll();
    }
    @GetMapping(ApiPathConstants.GET_CUSTOMER_BY_ID)
    public GetCustomerResponse getById(int id){
        return customerService.getById(id);
    }
    @PostMapping(ApiPathConstants.ADD_CUSTOMER)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCustomerRequest addCustomerRequest) {
        this.customerService.customAdd(addCustomerRequest);
    }

    @PutMapping(ApiPathConstants.UPDATE_CUSTOMER)
    public void update(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest){
        customerService.customUpdate(updateCustomerRequest);
    }

    @DeleteMapping(ApiPathConstants.DELETE_CUSTOMER)
    public void delete(@PathVariable int id){
        customerService.delete(id);
    }
}
