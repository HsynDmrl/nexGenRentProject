package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleEntityNotFound(EntityNotFoundException exception) {
        String errorMessage = exception.getMessage();
        return new ApiErrorResponse(HttpStatus.NOT_FOUND, errorMessage);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrorResponse handleAccessDenied(AccessDeniedException exception) {
        String errorMessage = exception.getMessage();
        return new ApiErrorResponse(HttpStatus.FORBIDDEN, errorMessage);
    }
}
