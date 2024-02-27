package com.nexgencarrental.nexGenCarRental.services.dtos.responses.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String gearType;
    private String fuelType;
    private String modelName;
    private String colorName;
    private String brandName;

}
