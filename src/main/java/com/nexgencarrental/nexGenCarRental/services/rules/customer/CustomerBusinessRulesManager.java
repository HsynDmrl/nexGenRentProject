package com.nexgencarrental.nexGenCarRental.services.rules.customer;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import com.nexgencarrental.nexGenCarRental.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerBusinessRulesManager implements CustomerBusinessRulesService {
    private CustomerRepository customerRepository;
}
