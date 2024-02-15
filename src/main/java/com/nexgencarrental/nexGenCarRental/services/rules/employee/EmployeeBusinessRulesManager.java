package com.nexgencarrental.nexGenCarRental.services.rules.employee;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Employee;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.NO_EMPLOYEE_FOUND;

@Service
@AllArgsConstructor
public class EmployeeBusinessRulesManager implements EmployeeBusinessRulesService {
    private final EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployee(int employeeId, boolean nullifyReferences) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (nullifyReferences) {
                List<Rental> rentals = employee.getRentals();
                for (Rental rental : rentals) {
                    rental.setEmployee(null);
                }
            }
            employeeRepository.delete(employee);
        } else {
            throw new DataNotFoundException(NO_EMPLOYEE_FOUND);
        }
    }
}
