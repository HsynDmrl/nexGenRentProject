package com.nexgencarrental.nexGenCarRental.services.dtos.requests.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddModelRequest {
    @Size(min = 2, message = "Enter a model name consisting of at least 2 letters")
    @Pattern(regexp = "^[A-Z][a-z0-9]{0,13}$", message = "Enter the first letter in CAPITAL and the following letters in SMALL. (Ex: 'Focus')")
    private String name;

    @Positive(message = "Brand Id must be a positive value.")
    private int brandId;
}
