package com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.PasswordConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {

    @NotBlank(message = PasswordConstants.PASSWORD_EMAIL_NOT_BLANK)
    @Email(message = PasswordConstants.PASSWORD_EMAIL_FORMAT)
    private String email;

    @NotBlank(message = PasswordConstants.OLD_PASSWORD_NOT_BLANK)
    private String oldPassword;

    @NotBlank(message = PasswordConstants.NEW_PASSWORD_NOT_BLANK)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{8,16}$", message = PasswordConstants.NEW_PASSWORD_PATTERN)
    private String newPassword;
}
