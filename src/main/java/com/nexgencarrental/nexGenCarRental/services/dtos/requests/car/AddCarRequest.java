package com.nexgencarrental.nexGenCarRental.services.dtos.requests.car;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.CarConstants;
import com.nexgencarrental.nexGenCarRental.entities.concretes.FuelType;
import com.nexgencarrental.nexGenCarRental.entities.concretes.GearType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest {

    @Min(value = 0, message = CarConstants.CAR_KILOMETER_MIN)
    @Max(value = 1000000, message = CarConstants.CAR_KILOMETER_MAX)
    @NotNull(message = CarConstants.CAR_KILOMETER_NULL)
    private double kilometer;

    @Min(value = 2005, message = CarConstants.CAR_YEAR_MIN)
    @Max(value = 2024, message = CarConstants.CAR_YEAR_MAX)
    @NotNull(message = CarConstants.CAR_YEAR_NULL)
    private short year;

    @DecimalMin(value = "0.0", inclusive = true, message = CarConstants.CAR_DAILY_PRICE_MIN)
    @NotNull(message = CarConstants.CAR_DAILY_PRICE_NULL)
    private double dailyPrice;

    @Pattern(regexp = "^\\d{1,2}\\s[A-Z]{1,3}\\s\\d{1,4}$", message = CarConstants.CAR_PLATE_PATTERN)
    @NotBlank(message = CarConstants.CAR_PLATE_BLANK)
    private String plate;

    @NotBlank(message = CarConstants.CAR_IMAGE_BLANK)
    private String imagePath;

    private boolean isStatus;

    @NotNull(message = CarConstants.CAR_GEAR_NULL)
    private GearType gearType;

    @NotNull(message = CarConstants.CAR_FUEL_NULL)
    private FuelType fuelType;

    @PositiveOrZero(message = CarConstants.CAR_MODEL_ID_POSITIVE)
    @NotNull(message = CarConstants.CAR_MODEL_ID_NULL)
    private int modelId;

    @PositiveOrZero(message = CarConstants.CAR_COLOR_ID_POSITIVE)
    @NotNull(message = CarConstants.CAR_COLOR_ID_NULL)
    private int colorId;

   /* // Fotoğraflar için yeni alan
    private List<MultipartFile> images;*/

}
