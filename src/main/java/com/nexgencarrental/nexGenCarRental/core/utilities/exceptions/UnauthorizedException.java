package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ForbiddenConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.UnauthorizedConstants;
import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {
    private final UnauthorizedConstants unauthorizedConstants;

    public UnauthorizedException(UnauthorizedConstants unauthorizedConstants) {
        super(unauthorizedConstants.getErrorMessage());
        this.unauthorizedConstants = unauthorizedConstants;
    }
}
