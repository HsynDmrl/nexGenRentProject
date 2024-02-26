package com.nexgencarrental.nexGenCarRental.services.dtos.requests.user;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.validation.UserConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {

    @Size(min = 2, max = 30, message = UserConstants.NAME_SIZE)
    @NotBlank(message = UserConstants.NAME_BLANK)
    @Pattern(regexp = "^[A-ZÇĞİÖŞÜ][a-zçğıöşü]+(\\s[A-ZÇĞİÖŞÜ][a-zçğıöşü]+)*$", message = UserConstants.NAME_PATTERN)
    private String name;

    @Size(min = 2, max = 20, message = UserConstants.SURNAME_SIZE)
    @NotBlank(message = UserConstants.SURNAME_BLANK)
    @Pattern(regexp = "^[A-ZÇĞİÖŞÜ][a-zçğıöşü]+$", message = UserConstants.SURNAME_PATTERN)
    private String surname;

    @NotBlank(message = UserConstants.EMAIL_BLANK)
    @Email(message = UserConstants.EMAIL_FORMAT)
    private String email;

    @NotBlank(message = UserConstants.NATIONALITY_BLANK)
    //@Size(min = 11, max = 11, message = UserConstants.NATIONALITY_SIZE)
    @Pattern(regexp = "\\d{11}", message = UserConstants.NATIONALITY_PATTERN)
    private String nationalityId;

    @NotNull(message = UserConstants.NATIONALITY_BLANK)
    private int roleId;

    @NotBlank(message = UserConstants.GSM_BLANK)
    @Pattern(regexp = "\\d{11}", message = UserConstants.GSM_PATTERN)
    //@Size(min = 11, max = 11, message = UserConstants.GSM_SIZE )
    private String gsm;
}
