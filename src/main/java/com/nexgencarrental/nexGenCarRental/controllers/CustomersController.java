package com.nexgencarrental.nexGenCarRental.controllers;
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
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomersController {
    private final CustomerService customerService;
    @GetMapping("/getAll")
    public List<GetCustomerListResponse> getAll(){
        return customerService.getAll();
    }
    @GetMapping("/{id}")
    public GetCustomerResponse getById(int id){
        return customerService.getById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCustomerRequest addCustomerRequest) {
        this.customerService.customAdd(addCustomerRequest);
    }

    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest){
        customerService.customUpdate(updateCustomerRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        customerService.delete(id);
    }
}
