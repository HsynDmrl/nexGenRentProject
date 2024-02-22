package com.nexgencarrental.nexGenCarRental.repositories;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    boolean existsByPlate(String plate);

    @Query("SELECT c FROM Car c WHERE " +
            "c.isStatus = TRUE AND " +
            "(:brandId IS NULL OR c.model.brand.id = :brandId) AND " +
            "(:modelId IS NULL OR c.model.id = :modelId) AND " +
            "(:year IS NULL OR c.year = :year) AND " +
            "(:colorId IS NULL OR c.color.id = :colorId) AND " +
            "(:gearType IS NULL OR c.gearType = :gearType) AND " +
            "(:fuelType IS NULL OR c.fuelType = :fuelType) AND " +
            "(:minDailyPrice IS NULL OR c.dailyPrice >= :minDailyPrice) AND " +
            "(:maxDailyPrice IS NULL OR c.dailyPrice <= :maxDailyPrice)")
    List<Car> findAllEntityFilter(
            @Param("brandId") Integer brandId,
            @Param("modelId") Integer modelId,
            @Param("year") Short year,
            @Param("colorId") Integer colorId,
            @Param("gearType") String gearType,
            @Param("fuelType") String fuelType,
            @Param("minDailyPrice") Double minDailyPrice,
            @Param("maxDailyPrice") Double maxDailyPrice);

    @Query("SELECT c FROM Car c WHERE " +
            "c.isStatus = TRUE AND (" +
            ":searchTerm IS NULL OR :searchTerm = '' OR " +
            "LOWER(c.model.brand.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(c.model.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Car> findAvailableCarsByNames(@Param("searchTerm") String searchTerm);
}
