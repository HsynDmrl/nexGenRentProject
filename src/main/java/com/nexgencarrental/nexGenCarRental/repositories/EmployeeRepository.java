package com.nexgencarrental.nexGenCarRental.repositories;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
