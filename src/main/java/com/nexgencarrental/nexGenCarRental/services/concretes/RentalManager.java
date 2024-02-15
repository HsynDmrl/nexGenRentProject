package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.RentalRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CustomerService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.EmployeeService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RentalService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.DeleteRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.rental.RentalBusinessRulesService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class RentalManager extends BaseManager<Rental, RentalRepository, GetRentalResponse, GetRentalListResponse,
        AddRentalRequest, UpdateRentalRequest> implements RentalService {
    private final CarService carService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final RentalBusinessRulesService rentalBusinessRulesService;

    public RentalManager(RentalRepository repository, ModelMapperService modelMapperService,
                         CarService carService, CustomerService customerService, EmployeeService employeeService,
                         RentalBusinessRulesService rentalBusinessRulesService) {
        super(repository, modelMapperService, GetRentalResponse.class, GetRentalListResponse.class, Rental.class,
                AddRentalRequest.class, UpdateRentalRequest.class);
        this.carService = carService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.rentalBusinessRulesService = rentalBusinessRulesService;
    }

    @Override
    public void customAdd(AddRentalRequest addRentalRequest) {
        carService.getById(addRentalRequest.getCarId()); // Car id kontrolü
        customerService.getById(addRentalRequest.getCustomerId()); // Customer id kontrolü
        employeeService.getById(addRentalRequest.getEmployeeId()); // Employee id kontrolü

        rentalBusinessRulesService.validateAddRentalRequest(addRentalRequest);

        Rental addRental = modelMapperService.forRequest().map(addRentalRequest, Rental.class);
        GetCarResponse carId = carService.getById(addRentalRequest.getCarId());

        addRental.setStartKilometer(carId.getKilometer());
        addRental.setTotalPrice(carId.getDailyPrice() * ChronoUnit.DAYS.between(addRentalRequest.getStartDate(), addRentalRequest.getEndDate()));
        addRental.setEndKilometer(null);
        addRental.setReturnDate(null);

        repository.save(addRental);
    }

    @Override
    public void customUpdate(UpdateRentalRequest updateRentalRequest) {
        getById(updateRentalRequest.getId()); // Rental id kontrolü
        carService.getById(updateRentalRequest.getCarId()); // Car id kontrolü
        customerService.getById(updateRentalRequest.getCustomerId()); // Customer id kontrolü
        employeeService.getById(updateRentalRequest.getEmployeeId()); // Employee id kontrolü

        rentalBusinessRulesService.validateUpdateRentalRequest(updateRentalRequest);

        Rental addRental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);

        GetCarResponse carId = carService.getById(updateRentalRequest.getCarId()); // Araç kilometresi otomatik olarak id'den alır.

        // totalPrice kontrolü
        addRental.setTotalPrice(carId.getDailyPrice() * ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate()));
        addRental.setStartKilometer(carId.getKilometer());
        addRental.setEndKilometer(null);
        addRental.setReturnDate(null);

        repository.save(addRental);
    }

    @Override
    public void customDelete(DeleteRentalRequest deleteRentalRequest) {
        rentalBusinessRulesService.validateDeleteRentalRequest(deleteRentalRequest.getId());
    }
}