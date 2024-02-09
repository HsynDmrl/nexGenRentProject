package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DataNotFoundEnum {

    ROLE_NOT_FOUND(5001, "Role not found"),
    ENTITY_NOT_FOUND(5007, "Entity not found."),
    ENTITY_NOT_FOUND_WITH_ID(5008, "Entity with ID %s not found."),
    USERS_NOT_FOUND(5011, "User not found with id: %s"),
    USER_NOT_FOUND(5003, "user.not.found"),
    NO_ENTITIES_FOUND(5009, "No entities found in the system."),
    FIND_REFRESH_TOKEN_ERROR(5010, "Error during finding refresh token by token"),
    NO_USER_FOUND(5029, "No user found!");

    private final Integer errorCode;
    private final String errorMessage;
}
