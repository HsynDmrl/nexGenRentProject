package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConflictEnum {
    DATA_CONFLICT(4091, "Data conflict occurred between resources."),
    RESOURCE_CONFLICT(4092, "Conflict occurred with the resource."),
    COLOR_NAME_ALREADY_EXISTS(4093, "Color name already exist!"),
    BRAND_NAME_ALREADY_EXISTS(4094,"Brand name already exist!"),
    MODEL_NAME_ALREADY_EXISTS(4095, "Model name already exist!"),
    USER_ALREADY_EXISTS(5000, "user.already.exists");


    private final Integer errorCode;
    private final String errorMessage;
}
