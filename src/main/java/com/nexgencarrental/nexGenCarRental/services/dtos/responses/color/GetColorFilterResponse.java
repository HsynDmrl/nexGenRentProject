package com.nexgencarrental.nexGenCarRental.services.dtos.responses.color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetColorFilterResponse {
    private int id;

    private String name;
}
