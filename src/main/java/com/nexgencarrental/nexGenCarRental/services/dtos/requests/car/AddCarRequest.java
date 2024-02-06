package com.nexgencarrental.nexGenCarRental.services.dtos.requests.car;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import com.nexgencarrental.nexGenCarRental.entities.concretes.FuelType;
import com.nexgencarrental.nexGenCarRental.entities.concretes.GearType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest {

    @Min(value = 0,message = ErrorConstants.KILOMETER_MIN)
    @Max(value = 1000000, message = ErrorConstants.KILOMETER_MAX )
    @NotNull(message = ErrorConstants.KILOMETER_NULL)
    private double kilometer;

    @Min(value = 2005, message = ErrorConstants.YEAR_MIN)
    @Max(value = 2024, message = ErrorConstants.YEAR_MAX)
    @NotNull(message = ErrorConstants.YEAR_NULL)
    private short year;

    @DecimalMin(value = "0.0", inclusive = true, message = ErrorConstants.DAILY_PRICE_MIN)
    @NotNull(message = ErrorConstants.DAILY_PRICE_NULL )
    private double dailyPrice;

    @Pattern(regexp = "^\\d{1,2}\\s[A-Z]{1,3}\\s\\d{1,4}$", message = ErrorConstants.PLATE_PATTERN)
    @NotBlank(message = ErrorConstants.PLATE_BLANK )
    private String plate;

    @NotBlank(message = ErrorConstants.IMAGE_BLANK )
    private String imagePath;

    @NotNull(message = ErrorConstants.GEAR_NULL )
    private GearType gearType;

    @NotNull(message = ErrorConstants.FUEL_NULL)
    private FuelType fuelType;

    @PositiveOrZero(message = ErrorConstants.MODEL_ID_POSITIVE)
    @NotNull(message = ErrorConstants.MODEL_ID_NULL )
    private int modelId;

    @PositiveOrZero(message = ErrorConstants.COLOR_ID_POSITIVE)
    @NotNull(message = ErrorConstants.COLOR_ID_NULL )
    private int colorId;

}
