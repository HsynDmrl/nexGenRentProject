package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DataNotFoundEnum {

    ROLE_NOT_FOUND(5001, "Role not found"),
    ENTITY_NOT_FOUND(5007, "Entity not found."),
    REFRESH_TOKEN_NOT_FOUND(5021, "Refresh token not found."),
    ENTITY_NOT_FOUND_WITH_ID(5008, "Entity with ID %s not found."),
    USER_NOT_FOUND(5003, "User not found."),

    NO_ENTITIES_FOUND(5009, "No entities found in the system."),
    FIND_REFRESH_TOKEN_ERROR(5010, "Error during finding refresh token by token"),
    NO_CARS_FOUND(5011, "No cars found to delete"),
    NO_COLOR_FOUND(5012, "No color found to delete"),
    NO_CUSTOMER_FOUND(5013, "No customer found to delete"),
    NO_EMPLOYEE_FOUND(5014, "No employee found to delete"),
    NO_MODEL_FOUND(5015, "No model found to delete"),
    NO_INVOICE_FOUND(5016, "No invoice found to delete"),
    INVOICE_NOT_FOUND(5018, "Invoice not found."),
    NO_BRAND_FOUND(5017, "No brand found to delete"),
    NO_USER_FOUND(5029, "No user found!");
    private final Integer errorCode;
    private final String errorMessage;
}
