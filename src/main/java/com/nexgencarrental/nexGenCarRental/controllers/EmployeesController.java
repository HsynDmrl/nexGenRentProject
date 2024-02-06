package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.services.abstracts.EmployeeService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.AddEmployeeRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPathConstants.EMPLOYEES_BASE_URL)
@AllArgsConstructor
public class EmployeesController {
    private final EmployeeService employeeService;
    @GetMapping(ApiPathConstants.GET_ALL_EMPLOYEES)
    public List<GetEmployeeListResponse> getAll(){
        return employeeService.getAll();
    }
    @GetMapping(ApiPathConstants.GET_EMPLOYEE_BY_ID)
    public GetEmployeeResponse getById(int id){
        return employeeService.getById(id);
    }
    @PostMapping(ApiPathConstants.ADD_EMPLOYEE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddEmployeeRequest addEmployeeRequest) {
        this.employeeService.customAdd(addEmployeeRequest);
    }

    @PutMapping(ApiPathConstants.UPDATE_EMPLOYEE)
    public void update(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest){
        employeeService.customUpdate(updateEmployeeRequest);
    }

    @DeleteMapping(ApiPathConstants.DELETE_EMPLOYEE)
    public void delete(@PathVariable int id){
        employeeService.delete(id);
    }
}
