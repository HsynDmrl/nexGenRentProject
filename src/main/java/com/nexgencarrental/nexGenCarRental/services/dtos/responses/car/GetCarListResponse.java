package com.nexgencarrental.nexGenCarRental.services.dtos.responses.car;

import com.nexgencarrental.nexGenCarRental.entities.concretes.FuelType;
import com.nexgencarrental.nexGenCarRental.entities.concretes.GearType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetCarListResponse {
    private int id;

    private String plate;

    private double kilometer;

    private double dailyPrice;

    private short year;

    private String imagePath;

    private boolean isStatus;

    private GearType gearType;

    private FuelType fuelType;

    private String modelName;

    private String colorName;

}
