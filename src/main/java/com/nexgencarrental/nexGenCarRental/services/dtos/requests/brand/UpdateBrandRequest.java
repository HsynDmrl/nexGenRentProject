package com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
    @Positive(message = "Id field cannot be less than 0.")
    private int id;

    @Size(min = 2,message = "Enter a brand consisting of at least 2 letters" )
    @Pattern(regexp = "^[A-Z][a-z]{1,13}$",message="Enter the first letter UPPER and the following letters SMALL without spaces.(Ex:'Ford'")
    private String name;

    private String logoPath;
}
