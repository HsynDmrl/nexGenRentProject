package com.nexgencarrental.nexGenCarRental.controllers;

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
@RequestMapping("/api/employess")
@AllArgsConstructor
public class EmployeesController {
    private final EmployeeService employeeService;
    @GetMapping("/getAll")
    public List<GetEmployeeListResponse> getAll(){
        return employeeService.getAll();
    }
    @GetMapping("/{id}")
    public GetEmployeeResponse getById(int id){
        return employeeService.getById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddEmployeeRequest addEmployeeRequest) {
        this.employeeService.customAdd(addEmployeeRequest);
    }

    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest){
        employeeService.customUpdate(updateEmployeeRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        employeeService.delete(id);
    }
}
