package com.nexgencarrental.nexGenCarRental.services.dtos.responses.role;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.base.GetBaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoleResponse extends GetBaseResponse {
    private String name;
}
