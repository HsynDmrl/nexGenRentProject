package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InternalServerEnum {

    DATABASE_ERROR(5001, "An error occurred while accessing the database."),
    EXTERNAL_SERVICE_ERROR(5002, "An error occurred while accessing external service."),
    INTERNAL_ERROR(5003, "An internal server error occurred."),
    TOKEN_GENERATION_ERROR(4001, "Error generating  token."),
    REFRESH_TOKEN_CREATION_ERROR(4002, "Error generating refresh token.");
    private final Integer errorCode;
    private final String errorMessage;
}
