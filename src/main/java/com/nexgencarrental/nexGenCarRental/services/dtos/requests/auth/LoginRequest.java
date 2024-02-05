package com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = ErrorConstants.EMAIL_BLANK)
    @Email(message = ErrorConstants.EMAIL_FORMAT)
    @Pattern(regexp = "[^@]+@[^\\.]+\\..+", message = ErrorConstants.EMAIL_PATTERN)
    private String email;

    @NotBlank(message = ErrorConstants.PASSWORD_BLANK)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message =ErrorConstants.PASSWORD_PATTERN)
    private String password;
}