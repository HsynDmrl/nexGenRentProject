package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ForbiddenConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.InternalServerConstants;
import lombok.Getter;

@Getter
public class InternalServerErrorException extends RuntimeException {
    private final InternalServerConstants internalServerConstants;

    public InternalServerErrorException(InternalServerConstants internalServerConstants) {
        super(internalServerConstants.getErrorMessage());
        this.internalServerConstants = internalServerConstants;
    }
}
