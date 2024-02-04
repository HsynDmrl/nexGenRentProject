package com.nexgencarrental.nexGenCarRental.services.dtos.requests.car;

import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.ErrorConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest {

    @Min(value = 0,message = ErrorConstants.KILOMETER_MIN)
    private double kilometer;

    @Min(value = 2005, message = ErrorConstants.YEAR_MIN)
    @Max(value = 2024, message = ErrorConstants.YEAR_MAX)
    private int year;

    //inclusive true olmalı çünkü inclusive=false değerinde 0'ı dahil etmiyor.
    @DecimalMin(value = "0.0", inclusive = true, message = ErrorConstants.DAILY_PRICE_MIN)
    private double dailyPrice;

    @Pattern(regexp = "^\\d{1,2}\\s[A-Z]{1,3}\\s\\d{1,4}$", message = ErrorConstants.PLATE_PATTERN)
    private String plate;

    //@PositiveOrZero yazmamız yeterli (@Positive de 0 dahil etmiyor)
    @PositiveOrZero(message = ErrorConstants.MODEL_ID_POSITIVE)
    private int modelId;

    @PositiveOrZero(message = ErrorConstants.COLOR_ID_POSITIVE)
    private int colorId;

}
