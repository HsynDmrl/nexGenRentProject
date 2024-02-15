package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Employee;
import com.nexgencarrental.nexGenCarRental.repositories.EmployeeRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.AddEmployeeRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeResponse;

public interface EmployeeService extends BaseService<Employee, EmployeeRepository, GetEmployeeResponse, GetEmployeeListResponse, AddEmployeeRequest, UpdateEmployeeRequest> {
    void customAdd(AddEmployeeRequest addEmployeeRequest);

    void customUpdate(UpdateEmployeeRequest updateEmployeeRequest);

    void customDelete(int id);
}
