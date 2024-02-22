package com.nexgencarrental.nexGenCarRental.services.dtos.responses.car;

import com.nexgencarrental.nexGenCarRental.entities.concretes.FuelType;
import com.nexgencarrental.nexGenCarRental.entities.concretes.GearType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetCarFilterResponse {

    private int id;

    private double kilometer;

    private short year;

    private double dailyPrice;

    private String plate;

    private String imagePath;

    private boolean isStatus;

    private String gearType; // Enum yerine String türünde
    private String fuelType; // Enum yerine String türünde

    private String modelName;
    private String colorName;
    private String brandName;

}
