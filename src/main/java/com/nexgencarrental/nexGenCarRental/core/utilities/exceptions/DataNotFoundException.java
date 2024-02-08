package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundConstants;
import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {
    private final DataNotFoundConstants dataNotFoundConstants;

    public DataNotFoundException(DataNotFoundConstants dataNotFoundConstants){
        super(dataNotFoundConstants.getErrorMessage());
        this.dataNotFoundConstants = dataNotFoundConstants;
    }
}
