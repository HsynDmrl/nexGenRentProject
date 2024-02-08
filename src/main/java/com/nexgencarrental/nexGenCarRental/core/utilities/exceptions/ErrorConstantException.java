package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorConstants;
import lombok.Getter;

@Getter
public class ErrorConstantException extends RuntimeException{
    private final ErrorConstants errorConstants;

    public ErrorConstantException(ErrorConstants errorConstants){
        super(errorConstants.getErrorMessage());
        this.errorConstants=errorConstants;
    }
}
