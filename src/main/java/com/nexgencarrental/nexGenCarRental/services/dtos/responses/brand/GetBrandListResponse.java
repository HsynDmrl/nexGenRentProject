package com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.base.GetBaseListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBrandListResponse extends GetBaseListResponse {

    private String name;

    private String logoPath;
}