package com.nexgencarrental.nexGenCarRental.services.rules.employee;

public interface EmployeeBusinessRulesService {
    void deleteEmployee(int employeeId, boolean nullifyReferences);
}
