package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum;
import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {
    private final ConflictEnum conflictEnum;

    public ConflictException(ConflictEnum conflictEnum) {
        super(conflictEnum.getErrorMessage());
        this.conflictEnum = conflictEnum;
    }
}
