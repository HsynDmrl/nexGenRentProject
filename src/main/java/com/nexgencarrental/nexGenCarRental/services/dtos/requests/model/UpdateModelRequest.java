package com.nexgencarrental.nexGenCarRental.services.dtos.requests.model;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateModelRequest {
    @Positive(message = "Id cannot be less than 0")
    private int id;

    @Size(min = 2,message = "The model entered must have at least 2 letters.")
    private String name;

    @Positive(message = "Brand Id cannot be less than 0")
    private int brandId;
}
