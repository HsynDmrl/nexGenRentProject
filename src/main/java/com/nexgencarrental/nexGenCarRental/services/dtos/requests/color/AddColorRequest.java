package com.nexgencarrental.nexGenCarRental.services.dtos.requests.color;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddColorRequest {
    @Size(min = 2,message = "Enter a color consisting of at least 2 letters" )
    @Pattern(regexp = "^[A-Z][a-z]{1,13}$",message="Enter the first letter UPPER and the following letters SMALL.(Ex:'White'")
    private String name;
}
