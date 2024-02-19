package com.nexgencarrental.nexGenCarRental.services.dtos.responses.color;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.base.GetBaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetColorResponse extends GetBaseResponse {
    private String name;
}
