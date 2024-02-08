package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ForbiddenConstants;
import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException {
    ForbiddenConstants forbiddenConstants;

    public ForbiddenException(ForbiddenConstants forbiddenConstants){
        super(forbiddenConstants.getErrorMessage());
        this.forbiddenConstants = forbiddenConstants;
    }
}
