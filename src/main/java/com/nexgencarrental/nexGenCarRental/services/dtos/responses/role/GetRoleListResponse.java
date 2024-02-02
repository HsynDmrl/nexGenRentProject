package com.nexgencarrental.nexGenCarRental.services.dtos.responses.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoleListResponse {
    private int id;
    private String name;
}
