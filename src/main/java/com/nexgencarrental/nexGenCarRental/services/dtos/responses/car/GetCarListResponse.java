package com.nexgencarrental.nexGenCarRental.services.dtos.responses.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetCarListResponse {
    private String plate;
    private double kilometer;
    private double dailyPrice;
    private int year;
    private String modelName;
    private String colorName;

}
