package com.nexgencarrental.nexGenCarRental.repositories;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findByCarId(int carId);
    @Query("SELECT DISTINCT r.car FROM Rental r WHERE r.startDate <= :endDate AND r.endDate >= :startDate AND r.car.isStatus = true")
    List<Car> findAvailableByDates(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);;
}
