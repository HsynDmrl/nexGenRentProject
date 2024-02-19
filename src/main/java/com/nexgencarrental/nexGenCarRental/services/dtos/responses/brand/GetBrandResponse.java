package com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.base.GetBaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBrandResponse extends GetBaseResponse {
    private String name;

    private String logoPath;
}
