package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ForbiddenConstants;
import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException{
    private final ConflictConstants conflictConstants;

    public ConflictException(ConflictConstants conflictConstants) {
        super(conflictConstants.getErrorMessage());
        this.conflictConstants = conflictConstants;
    }
}
