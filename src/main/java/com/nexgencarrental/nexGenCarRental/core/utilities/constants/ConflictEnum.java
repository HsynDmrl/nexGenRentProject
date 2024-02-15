package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConflictEnum {
    DATA_CONFLICT(4091, "Data conflict occurred between resources."),
    RESOURCE_CONFLICT(4092, "Conflict occurred with the resource."),
    COLOR_NAME_ALREADY_EXISTS(4093, "Color name already exist!"),
    BRAND_NAME_ALREADY_EXISTS(4094, "Brand name already exist!"),
    MODEL_NAME_ALREADY_EXISTS(4095, "Model name already exist!"),
    NO_INVOICE_ALREADY_EXISTS(4096, "The invoice number is already in use"),
    NO_ROLE_ALREADY_EXISTS(4097, "The role number is already in use"),
    USER_ALREADY_EXISTS(5000, "user.already.exists"),
    PLATE_ALREADY_EXISTS(4020, "Car license plate already registered"),
    CUSTOMER_ALREADY_EXISTS(4021, "This customer is already registered");


    private final Integer errorCode;
    private final String errorMessage;
}
