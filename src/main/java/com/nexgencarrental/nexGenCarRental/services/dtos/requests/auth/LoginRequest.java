package com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.LoginConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = LoginConstants.LOGIN_NOT_BLANK)
    @Email(message = LoginConstants.LOGIN_EMAIL_FORMAT)
    private String email;

    @NotBlank(message = LoginConstants.LOGIN_PASSWORD_BLANK)
    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{8,16}$", message = LoginConstants.LOGIN_PASSWORD_PATTERN)
    private String password;
}