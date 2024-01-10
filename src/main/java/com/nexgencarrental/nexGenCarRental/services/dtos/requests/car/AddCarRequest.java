package com.nexgencarrental.nexGenCarRental.services.dtos.requests.car;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest {

    @Min(value = 0,message = "Enter zero or a value greater than zero")
    private double kilometer;

    @Min(value = 2005, message = "Between 2005 and 2024")
    @Max(value = 2024, message = "No older than 2024")
    private int year;

    //inclusive true olmalı çünkü inclusive=false değerinde 0'ı dahil etmiyor.
    @DecimalMin(value = "0.0", inclusive = true, message = "Daily price cannot be less than zero")
    private double dailyPrice;

    @Pattern(regexp = "^\\d{1,2}\\s[A-Z]{1,3}\\s\\d{1,4}$", message = "'34 ABC 456' enter according to this format")
    private String plate;

    //@PositiveOrZero yazmamız yeterli (@Positive de 0 dahil etmiyor)
    @PositiveOrZero(message = "Model Id cannot be less than 0.")
    private int modelId;

    @PositiveOrZero(message = "Color Id cannot be less than 0.")
    private int colorId;

}
