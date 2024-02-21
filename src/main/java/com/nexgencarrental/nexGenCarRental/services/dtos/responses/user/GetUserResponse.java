package com.nexgencarrental.nexGenCarRental.services.dtos.responses.user;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.base.GetBaseResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.role.GetRoleListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse extends GetBaseResponse {

    private String name;

    private String surname;

    private String email;

    private String nationalityId;

    private String gsm;

    private GetRoleListResponse role;
}
