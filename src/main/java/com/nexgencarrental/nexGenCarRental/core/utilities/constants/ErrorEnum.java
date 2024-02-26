package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorEnum {
    LOGIN_ERROR(5005, "Login error"),
    DATE_ERROR(5020, "Enter a valid date"),
    AUTH_RESPONSE_ERROR(5006, "Auth response error"),
    SEARCH_RESPONSE_ERROR(5007, "The search term cannot be empty."),
    REFRESH_TOKEN_EXPIRED(5014, "Refresh token was expired."),
    DELETE_REFRESH_TOKEN_ERROR(5013, "Error during deleting refresh tokens by user id"),
    CREATE_REFRESH_TOKEN_ERROR(5012, "Error during creating refresh token"),
    ERROR_GETTING_USER_BY_EMAIL(5019, "Error during getting user by email"),
    REGISTRATION_ERROR(5004, "Registration error");

    private final Integer errorCode;
    private final String errorMessage;
}
