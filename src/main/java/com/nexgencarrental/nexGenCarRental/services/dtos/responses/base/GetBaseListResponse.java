package com.nexgencarrental.nexGenCarRental.services.dtos.responses.base;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.DatabaseConstants;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetBaseListResponse {
    private int id;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
