package com.nexgencarrental.nexGenCarRental.services.rules.customer;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Customer;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ENTITY_NOT_FOUND;

@Service
@AllArgsConstructor
public class CustomerBusinessRulesManager implements CustomerBusinessRulesService {
    private final CustomerRepository customerRepository;

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
            throw new DataNotFoundException(ENTITY_NOT_FOUND);
        }
    }
}
