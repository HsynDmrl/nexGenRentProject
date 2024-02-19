package com.nexgencarrental.nexGenCarRental.services.dtos.responses.color;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.base.GetBaseListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetColorListResponse extends GetBaseListResponse {
    private String name;
}
