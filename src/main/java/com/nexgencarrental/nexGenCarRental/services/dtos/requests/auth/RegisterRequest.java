package com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Size(min = 2,message = ErrorConstants.NAME_SIZE)
    @NotBlank(message = ErrorConstants.NAME_BLANK)
    @Pattern(regexp = "^[A-Z][a-z]$",message= ErrorConstants.NAME_PATTERN)
    private String name;

    @Size(min = 2,message = ErrorConstants.SURNAME_SIZE)
    @NotBlank(message =ErrorConstants.SURNAME_BLANK )
    @Pattern(regexp = "^[A-Z][a-z]$",message=ErrorConstants.SURNAME_PATTERN)
    private String surname;

    @NotBlank(message = ErrorConstants.NATIONALITY_BLANK)
    @Pattern(regexp = "\\d{11}", message =ErrorConstants.NATIONALITY_PATTERN)
    private String nationalityId;

    @NotBlank(message =ErrorConstants.GSM_BLANK)
    @Pattern(regexp = "\\d{11}", message = ErrorConstants.GSM_PATTERN)
    private String gsm;

    @NotBlank(message = ErrorConstants.EMAIL_BLANK)
    @Email(message = ErrorConstants.EMAIL_FORMAT)
    @Pattern(regexp = "[^@]+@[^\\.]+\\..+", message = ErrorConstants.EMAIL_PATTERN)
    private String email;

    @NotBlank(message = ErrorConstants.PASSWORD_BLANK)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message =ErrorConstants.PASSWORD_PATTERN)
    private String password;

    @NotNull(message = ErrorConstants.ROLE_BLANK)
    @Positive(message = ErrorConstants.ROLE_ID_POSITIVE)
    private Integer roleId;
}
