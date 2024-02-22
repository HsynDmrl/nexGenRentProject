package com.nexgencarrental.nexGenCarRental.services.dtos.responses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelFilterResponse {
    private int id;
    private String name;
    private String brandName;
}
