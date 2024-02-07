package com.nexgencarrental.nexGenCarRental.services.dtos.requests.user;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    @Size(min = 2,message = ApplicationConstants.NAME_SIZE)
    @NotBlank(message = ApplicationConstants.NAME_BLANK)
    @Pattern(regexp = "^[A-Z][a-z]",message= ApplicationConstants.NAME_PATTERN)
    private String name;

    @Size(min = 2,message = ApplicationConstants.SURNAME_SIZE)
    @NotBlank(message =ApplicationConstants.SURNAME_BLANK )
    @Pattern(regexp = "^[A-Z][a-z]",message=ApplicationConstants.SURNAME_PATTERN)
    private String surname;

    @NotBlank(message = ApplicationConstants.EMAIL_BLANK)
    @Email(message = ApplicationConstants.EMAIL_FORMAT)
    @Pattern(regexp = "^[A-Z][a-z]", message = ApplicationConstants.EMAIL_PATTERN)
    private String email;

    @NotBlank(message = ApplicationConstants.NATIONALITY_BLANK)
    @Pattern(regexp = "\\d{11}", message =ApplicationConstants.NATIONALITY_PATTERN)
    private String nationalityId;

    @NotBlank(message =ApplicationConstants.GSM_BLANK)
    @Pattern(regexp = "\\d{11}", message = ApplicationConstants.GSM_PATTERN)
    private String gsm;
}
