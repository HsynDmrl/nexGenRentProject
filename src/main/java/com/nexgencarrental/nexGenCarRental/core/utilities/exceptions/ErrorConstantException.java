package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorEnum;
import lombok.Getter;

@Getter
public class ErrorConstantException extends RuntimeException {
    private final ErrorEnum errorEnum;

    public ErrorConstantException(ErrorEnum errorEnum) {
        super(errorEnum.getErrorMessage());
        this.errorEnum = errorEnum;
    }
}
