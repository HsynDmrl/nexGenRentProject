package com.nexgencarrental.nexGenCarRental.services.dtos.responses.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetBaseResponse {
    private int id;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
