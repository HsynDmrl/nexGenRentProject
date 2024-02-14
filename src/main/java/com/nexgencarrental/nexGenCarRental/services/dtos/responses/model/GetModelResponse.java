package com.nexgencarrental.nexGenCarRental.services.dtos.responses.model;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelResponse {
    private int id;

    private String name;

    private GetBrandListResponse brand;
}
