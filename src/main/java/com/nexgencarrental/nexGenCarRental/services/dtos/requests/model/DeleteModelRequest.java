package com.nexgencarrental.nexGenCarRental.services.dtos.requests.model;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteModelRequest {
    @Positive(message = "Id positive number!")
    private int id;
}
