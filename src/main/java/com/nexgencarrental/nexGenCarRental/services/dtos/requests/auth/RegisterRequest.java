package com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Size(min = 2,message = ApplicationConstants.NAME_SIZE)
    @NotBlank(message = ApplicationConstants.NAME_BLANK)
    @Pattern(regexp = "^[A-Z][a-z]$",message= ApplicationConstants.NAME_PATTERN)
    private String name;

    @Size(min = 2,message = ApplicationConstants.SURNAME_SIZE)
    @NotBlank(message =ApplicationConstants.SURNAME_BLANK )
    @Pattern(regexp = "^[A-Z][a-z]$",message=ApplicationConstants.SURNAME_PATTERN)
    private String surname;

    @NotBlank(message = ApplicationConstants.NATIONALITY_BLANK)
    @Pattern(regexp = "\\d{11}", message =ApplicationConstants.NATIONALITY_PATTERN)
    private String nationalityId;

    @NotBlank(message =ApplicationConstants.GSM_BLANK)
    @Pattern(regexp = "\\d{11}", message = ApplicationConstants.GSM_PATTERN)
    private String gsm;

    @NotBlank(message = ApplicationConstants.EMAIL_BLANK)
    @Email(message = ApplicationConstants.EMAIL_FORMAT)
    @Pattern(regexp = "[^@]+@[^\\.]+\\..+", message = ApplicationConstants.EMAIL_PATTERN)
    private String email;

    @NotBlank(message = ApplicationConstants.PASSWORD_BLANK)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message =ApplicationConstants.PASSWORD_PATTERN)
    private String password;

    @NotNull(message = ApplicationConstants.ROLE_BLANK)
    @Positive(message = ApplicationConstants.ROLE_ID_POSITIVE)
    private Integer roleId;
}
