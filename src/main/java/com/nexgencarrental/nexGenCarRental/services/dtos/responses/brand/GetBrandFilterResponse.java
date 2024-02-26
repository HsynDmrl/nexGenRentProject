package com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetBrandFilterResponse {
    private int id;

    private String name;

    private String logoPath;
}
