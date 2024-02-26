package com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.RegisterConstants;
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
public class RegisterRequest {

    @Size(min = 2, max = 30, message = RegisterConstants.REGISTER_NAME_SIZE)
    @NotBlank(message = RegisterConstants.REGISTER_NAME_BLANK)
    @Pattern(regexp = "^[A-ZÇĞİÖŞÜ][a-zçğıöşü]+(\\s[A-ZÇĞİÖŞÜ][a-zçğıöşü]+)*$", message = RegisterConstants.REGISTER_NAME_PATTERN)
    private String name;

    @Size(min = 2, max = 20, message = RegisterConstants.REGISTER_SURNAME_SIZE)
    @NotBlank(message = RegisterConstants.REGISTER_SURNAME_BLANK)
    @Pattern(regexp = "^[A-ZÇĞİÖŞÜ][a-zçğıöşü]+$", message = RegisterConstants.REGISTER_SURNAME_PATTERN)
    private String surname;

    @NotBlank(message = RegisterConstants.REGISTER_NATIONALITY_BLANK)
    @Pattern(regexp = "\\d{11}", message = RegisterConstants.REGISTER_NATIONALITY_PATTERN)
    private String nationalityId;

    @NotBlank(message = RegisterConstants.REGISTER_GSM_BLANK)
    @Pattern(regexp = "\\d{11}", message = RegisterConstants.REGISTER_GSM_PATTERN)
    private String gsm;

    @NotBlank(message = RegisterConstants.REGISTER_EMAIL_BLANK)
    @Email(message = RegisterConstants.REGISTER_EMAIL_PATTERN)
    private String email;

    @NotBlank(message = RegisterConstants.REGISTER_PASSWORD_BLANK)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{8,16}$", message = RegisterConstants.REGISTER_PASSWORD_PATTERN)
    private String password;
}
