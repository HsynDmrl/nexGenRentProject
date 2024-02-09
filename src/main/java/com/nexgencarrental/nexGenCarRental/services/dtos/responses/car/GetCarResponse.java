package com.nexgencarrental.nexGenCarRental.services.dtos.responses.car;

import com.nexgencarrental.nexGenCarRental.entities.concretes.FuelType;
import com.nexgencarrental.nexGenCarRental.entities.concretes.GearType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetCarResponse {
    private double kilometer;

    private short year;

    private double dailyPrice;

    private String plate;

    private String imagePath;

    private GearType gearType;

    private FuelType fuelType;

    private String modelName;

    private String colorName;
}

