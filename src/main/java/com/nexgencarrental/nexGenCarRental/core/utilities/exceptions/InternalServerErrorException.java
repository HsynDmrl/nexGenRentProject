package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.InternalServerEnum;
import lombok.Getter;

@Getter
public class InternalServerErrorException extends RuntimeException {
    private final InternalServerEnum internalServerEnum;

    public InternalServerErrorException(InternalServerEnum internalServerEnum) {
        super(internalServerEnum.getErrorMessage());
        this.internalServerEnum = internalServerEnum;
    }
}
