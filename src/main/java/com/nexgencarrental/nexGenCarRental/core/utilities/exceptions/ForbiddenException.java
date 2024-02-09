package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ForbiddenEnum;
import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException {
    private final ForbiddenEnum forbiddenEnum;

    public ForbiddenException(ForbiddenEnum forbiddenEnum) {
        super(forbiddenEnum.getErrorMessage());
        this.forbiddenEnum = forbiddenEnum;
    }
}
