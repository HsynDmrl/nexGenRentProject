package com.nexgencarrental.nexGenCarRental.services.dtos.responses.user;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.base.GetBaseListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserListResponse extends GetBaseListResponse {
    private String name;

    private String surname;

    private String email;

    private String nationalityId;

    private String gsm;

    private String roleName;
}
