package com.nexgencarrental.nexGenCarRental.services.dtos.requests.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private int id;

    private String name;

    private String surname;

    private String email;

    private String nationalityId;

    private String gsm;


}
