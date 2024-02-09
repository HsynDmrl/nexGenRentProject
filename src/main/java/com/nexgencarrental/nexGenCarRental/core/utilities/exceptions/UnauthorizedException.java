package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.UnauthorizedEnum;
import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {
    private final UnauthorizedEnum unauthorizedEnum;

    public UnauthorizedException(UnauthorizedEnum unauthorizedEnum) {
        super(unauthorizedEnum.getErrorMessage());
        this.unauthorizedEnum = unauthorizedEnum;
    }
}
