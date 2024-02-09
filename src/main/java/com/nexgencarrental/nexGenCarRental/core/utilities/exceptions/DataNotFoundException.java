package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum;
import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {
    private final DataNotFoundEnum dataNotFoundEnum;

    public DataNotFoundException(DataNotFoundEnum dataNotFoundEnum) {
        super(dataNotFoundEnum.getErrorMessage());
        this.dataNotFoundEnum = dataNotFoundEnum;
    }
}
