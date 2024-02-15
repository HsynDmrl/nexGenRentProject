package com.nexgencarrental.nexGenCarRental.services.rules.customer;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Customer;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.CUSTOMER_ALREADY_EXISTS;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.NO_CUSTOMER_FOUND;

@Service
@AllArgsConstructor
public class CustomerBusinessRulesManager implements CustomerBusinessRulesService {
    private final CustomerRepository customerRepository;

    @Override
    public void checkIfCustomerExists(int customerId) {
        if (customerRepository.existsById(customerId)) {
            throw new ConflictException(CUSTOMER_ALREADY_EXISTS);
        }
    }

    @Override
    public void deleteCustomer(int customerId, boolean nullifyReferences) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (nullifyReferences) {
                List<Rental> rentals = customer.getRentals();
                for (Rental rental : rentals) {
                    rental.setCustomer(null);
                }
            }
            customerRepository.delete(customer);
        } else {
            throw new DataNotFoundException(NO_CUSTOMER_FOUND);
        }
    }
}
