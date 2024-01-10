package com.nexgencarrental.nexGenCarRental.services.rules.customer;

import com.nexgencarrental.nexGenCarRental.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerBusinessRulesManager implements CustomerBusinessRulesService {
    private CustomerRepository customerRepository;
    @Override
    public void existsByNationalityId(String name) {
        if (customerRepository.existsByNationalityId(name.trim().replaceAll("\\s", ""))){
            throw new RuntimeException("The Customer Nationality Id is already exists!");
        }
    }
}
