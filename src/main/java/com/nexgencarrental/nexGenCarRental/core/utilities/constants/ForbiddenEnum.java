package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ForbiddenEnum {

    ROLE_NOT_ALLOWED(4031, "You are not allowed to access this resource."),
    ENTITY_ACCESS_DENIED(4032, "Access to entity is denied."),
    ENTITY_ACCESS_DENIED_WITH_ID(4033, "Access to entity with ID  is denied."),
    USERS_ACCESS_DENIED(4034, "Access to user with id  is denied."),
    USER_ACCESS_DENIED(4035, "Access to user is id denied."),
    NO_ENTITIES_ACCESSIBLE(4036, "No entities are accessible in the system."),
    NO_USER_ACCESSIBLE(4037, "No user is accessible.");

    private final Integer errorCode;
    private final String errorMessage;
}
