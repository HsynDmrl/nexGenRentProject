package com.nexgencarrental.nexGenCarRental.services.rules.customer;

public interface CustomerBusinessRulesService {
    void deleteCustomer(int customerId, boolean nullifyReferences);
}
