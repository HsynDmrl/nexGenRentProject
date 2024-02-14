package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Customer;
import com.nexgencarrental.nexGenCarRental.repositories.CustomerRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.DeleteCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.AddCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.DeleteCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.UpdateCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerResponse;

public interface CustomerService extends BaseService<Customer, CustomerRepository, GetCustomerResponse,
        GetCustomerListResponse, AddCustomerRequest, UpdateCustomerRequest> {
    void customAdd(AddCustomerRequest addCustomerRequest);

    void customUpdate(UpdateCustomerRequest updateCustomerRequest);
    void customDelete(DeleteCustomerRequest deleteCustomerRequest);
}
