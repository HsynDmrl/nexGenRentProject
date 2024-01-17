package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Customer;
import com.nexgencarrental.nexGenCarRental.repositories.CustomerRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CustomerService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.*;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.*;
import com.nexgencarrental.nexGenCarRental.services.rules.customer.CustomerBusinessRulesService;
import org.springframework.stereotype.Service;
@Service
public class CustomerManager extends BaseManager<Customer, CustomerRepository, GetCustomerResponse,
        GetCustomerListResponse, AddCustomerRequest, UpdateCustomerRequest> implements CustomerService {
    private final UserService userService;
    private final CustomerBusinessRulesService customerBusinessRulesService;
    public CustomerManager(CustomerRepository repository, ModelMapperService modelMapperService,
                           CustomerBusinessRulesService customerBusinessRulesService, UserService userService) {
        super(repository, modelMapperService, GetCustomerResponse.class, GetCustomerListResponse.class,
                Customer.class, AddCustomerRequest.class, UpdateCustomerRequest.class);
        this.userService = userService;
        this.customerBusinessRulesService = customerBusinessRulesService;
    }
    @Override
    public void customAdd(AddCustomerRequest addCustomerRequest) {
        //userService.getById(addCustomerRequest.getUserId()); // User id kontrolü
        customerBusinessRulesService.existsByNationalityId(addCustomerRequest.getNationalityId()); // NationalityId kontrolü
        add(addCustomerRequest, Customer.class);
    }
    @Override
    public void customUpdate(UpdateCustomerRequest updateCustomerRequest) {
        //userService.getById(updateCustomerRequest.getUserId()); // User id kontrolü
        customerBusinessRulesService.existsByNationalityId(updateCustomerRequest.getNationalityId()); // NationalityId kontrolü
        update(updateCustomerRequest, Customer.class);
    }
}
