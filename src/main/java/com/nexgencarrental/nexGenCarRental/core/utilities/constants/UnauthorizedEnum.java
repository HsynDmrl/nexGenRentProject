package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UnauthorizedEnum {
    INVALID_CREDENTIALS(4011, "Invalid username or password."),
    EXPIRED_TOKEN(4012, "The authentication token has expired."),
    MISSING_TOKEN(4013, "Authentication token is missing.");

    private final Integer errorCode;
    private final String errorMessage;

}
