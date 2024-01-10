package com.nexgencarrental.nexGenCarRental.services.dtos.responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {
    private int id;

    private String name;

    private String surname;

    private String gsm;

    private String email;
}
