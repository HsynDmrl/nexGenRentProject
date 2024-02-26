package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.RentalRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CustomerService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.EmployeeService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RentalService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalAdminRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalAdminRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalAdminListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.rental.RentalBusinessRulesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.DATE_NOT_FOUND;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorEnum.LOGIN_ERROR;

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
    public List<GetRentalAdminListResponse> getAdminAll() {
        List<Rental> allRentals = repository.findAll();
        List<GetRentalAdminListResponse> adminResponses = new ArrayList<>();
        for (Rental rental : allRentals) {
            adminResponses.add(modelMapperService.forResponse().map(rental, GetRentalAdminListResponse.class));
        }
        return adminResponses;
    }

    @Override
    public GetRentalResponse getByAdminId(int id) {
        Rental rental = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(DATE_NOT_FOUND));
        return modelMapperService.forResponse().map(rental, GetRentalResponse.class);
    }

    @Override
    public void customAdd(AddRentalRequest addRentalRequest) {
        carService.getById(addRentalRequest.getCarId());
        customerService.getById(addRentalRequest.getCustomerId());
        employeeService.getById(addRentalRequest.getEmployeeId());

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
    public void rentalAdminAdd(AddRentalAdminRequest addRentalAdminRequest) {
        carService.getById(addRentalAdminRequest.getCarId());
        customerService.getById(addRentalAdminRequest.getCustomerId());
        employeeService.getById(addRentalAdminRequest.getEmployeeId());

        rentalBusinessRulesService.validateAdminRentalRequest(addRentalAdminRequest);

        Rental addRental = modelMapperService.forRequest().map(addRentalAdminRequest, Rental.class);
        addRental.setStartKilometer(addRentalAdminRequest.getStartKilometer());
        addRental.setEndKilometer(addRentalAdminRequest.getEndKilometer());
        addRental.setTotalPrice(addRentalAdminRequest.getTotalPrice());
        addRental.setDiscount(addRentalAdminRequest.getDiscount());


        GetCarResponse carInfo = carService.getById(addRentalAdminRequest.getCarId());
        double dailyPrice = carInfo.getDailyPrice();

        addRental.setStartKilometer(carInfo.getKilometer());
        addRental.setTotalPrice(dailyPrice * ChronoUnit.DAYS.between(addRentalAdminRequest.getStartDate(), addRentalAdminRequest.getEndDate()));
        addRental.setEndKilometer(null);
        addRental.setReturnDate(null);
    }

    @Override
    public void rentalAdminUpdate(UpdateRentalAdminRequest updateRentalAdminRequest) {
        carService.getById(updateRentalAdminRequest.getCarId());
        customerService.getById(updateRentalAdminRequest.getCustomerId());
        employeeService.getById(updateRentalAdminRequest.getEmployeeId());

        rentalBusinessRulesService.validateAdminRentalRequest(updateRentalAdminRequest);

        Rental addRental = modelMapperService.forRequest().map(updateRentalAdminRequest, Rental.class);
        addRental.setStartKilometer(updateRentalAdminRequest.getStartKilometer());
        addRental.setEndKilometer(updateRentalAdminRequest.getEndKilometer());
        addRental.setTotalPrice(updateRentalAdminRequest.getTotalPrice());
        addRental.setDiscount(updateRentalAdminRequest.getDiscount());


        GetCarResponse carInfo = carService.getById(updateRentalAdminRequest.getCarId());
        double dailyPrice = carInfo.getDailyPrice();

        addRental.setStartKilometer(carInfo.getKilometer());
        addRental.setTotalPrice(dailyPrice * ChronoUnit.DAYS.between(updateRentalAdminRequest.getStartDate(), updateRentalAdminRequest.getEndDate()));
        addRental.setEndKilometer(null);
        addRental.setReturnDate(null);
    }

    @Override
    public void customUpdate(UpdateRentalRequest updateRentalRequest) {
        getById(updateRentalRequest.getId());
        carService.getById(updateRentalRequest.getCarId());
        customerService.getById(updateRentalRequest.getCustomerId());
        employeeService.getById(updateRentalRequest.getEmployeeId());

        rentalBusinessRulesService.validateUpdateRentalRequest(updateRentalRequest);

        Rental addRental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);

        GetCarResponse carId = carService.getById(updateRentalRequest.getCarId());

        addRental.setTotalPrice(carId.getDailyPrice() * ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate()));
        addRental.setStartKilometer(carId.getKilometer());
        addRental.setEndKilometer(null);
        addRental.setReturnDate(null);

        repository.save(addRental);
    }

    @Override
    public void customDelete(int rentalId) {
        rentalBusinessRulesService.validateDeleteRentalRequest(rentalId);
    }

    @Override
    public List<Car> findAvailableByDates(LocalDate startDate, LocalDate endDate) {

        if (startDate == null || endDate == null) {
            throw new IllegalStateException(String.valueOf(LOGIN_ERROR));
        }

        List<Car> availableCars = repository.findAvailableByDates(startDate, endDate);

        if (availableCars.isEmpty()) {
            throw new DataNotFoundException(DATE_NOT_FOUND);
        }

        return availableCars;
    }
}