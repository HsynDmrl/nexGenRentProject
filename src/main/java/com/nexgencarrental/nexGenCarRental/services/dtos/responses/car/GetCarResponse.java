package com.nexgencarrental.nexGenCarRental.services.dtos.responses.car;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelListResponse;
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
    private String modelName;
    private String colorName;
}

