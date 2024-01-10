package com.nexgencarrental.nexGenCarRental.services.abstracts;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Customer;
import com.nexgencarrental.nexGenCarRental.repositories.CustomerRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.AddCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.UpdateCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerResponse;

import java.util.List;

public interface CustomerService extends BaseService<Customer, CustomerRepository, GetCustomerResponse,
        GetCustomerListResponse, AddCustomerRequest, UpdateCustomerRequest>{
    void customAdd(AddCustomerRequest addCustomerRequest);
    void customUpdate(UpdateCustomerRequest updateCustomerRequest);
}
