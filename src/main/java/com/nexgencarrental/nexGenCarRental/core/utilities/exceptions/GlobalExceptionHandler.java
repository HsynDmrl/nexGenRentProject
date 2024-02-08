package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ForbiddenConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictConstants;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrorResponse handleForbiddenException(ForbiddenException exception) {
        ForbiddenConstants errorConstants = exception.getForbiddenConstants();
        return new ApiErrorResponse(HttpStatus.FORBIDDEN, errorConstants.getErrorCode(), errorConstants.getErrorMessage());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleInternalServerErrorException(InternalServerErrorException exception) {
        return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, 5000, exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse handleUnauthorizedException(UnauthorizedException exception) {
        return new ApiErrorResponse(HttpStatus.UNAUTHORIZED, 4010, exception.getMessage());
    }
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handleConflictException(ConflictException exception) {
        return new ApiErrorResponse(HttpStatus.CONFLICT, ConflictConstants.DATA_CONFLICT.getErrorCode(), exception.getMessage());
    }
    @ExceptionHandler({
            EntityNotFoundException.class,
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFound(Exception exception) {
        String errorMessage = exception.getMessage();
        return new ApiErrorResponse(HttpStatus.NOT_FOUND, 0,errorMessage);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleValidationError(MethodArgumentNotValidException exception) {
        String validationErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, 3000, validationErrors);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleGenericException(Exception exception) {
        return new ApiErrorResponse(HttpStatus.FORBIDDEN, 4030, ForbiddenConstants.USERS_ACCESS_DENIED.getErrorMessage());
    }
}