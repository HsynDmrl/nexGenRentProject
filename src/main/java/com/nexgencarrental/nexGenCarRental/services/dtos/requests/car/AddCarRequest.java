package com.nexgencarrental.nexGenCarRental.services.dtos.requests.car;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
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

    @Min(value = 0,message = ApplicationConstants.KILOMETER_MIN)
    @Max(value = 1000000, message = ApplicationConstants.KILOMETER_MAX )
    @NotNull(message = ApplicationConstants.KILOMETER_NULL)
    private double kilometer;

    @Min(value = 2005, message = ApplicationConstants.YEAR_MIN)
    @Max(value = 2024, message = ApplicationConstants.YEAR_MAX)
    @NotNull(message = ApplicationConstants.YEAR_NULL)
    private short year;

    @DecimalMin(value = "0.0", inclusive = true, message = ApplicationConstants.DAILY_PRICE_MIN)
    @NotNull(message = ApplicationConstants.DAILY_PRICE_NULL )
    private double dailyPrice;

    @Pattern(regexp = "^\\d{1,2}\\s[A-Z]{1,3}\\s\\d{1,4}$", message = ApplicationConstants.PLATE_PATTERN)
    @NotBlank(message = ApplicationConstants.PLATE_BLANK )
    private String plate;

    @NotBlank(message = ApplicationConstants.IMAGE_BLANK )
    private String imagePath;

    @NotNull(message = ApplicationConstants.GEAR_NULL )
    private GearType gearType;

    @NotNull(message = ApplicationConstants.FUEL_NULL)
    private FuelType fuelType;

    @PositiveOrZero(message = ApplicationConstants.MODEL_ID_POSITIVE)
    @NotNull(message = ApplicationConstants.MODEL_ID_NULL )
    private int modelId;

    @PositiveOrZero(message = ApplicationConstants.COLOR_ID_POSITIVE)
    @NotNull(message = ApplicationConstants.COLOR_ID_NULL )
    private int colorId;

}
