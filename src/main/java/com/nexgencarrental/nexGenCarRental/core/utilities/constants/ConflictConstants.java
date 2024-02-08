package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConflictConstants {
    DATA_CONFLICT(4091, "Data conflict occurred between resources."),
    RESOURCE_CONFLICT(4092, "Conflict occurred with the resource.");

    private final Integer errorCode;
    private final String errorMessage;
}
