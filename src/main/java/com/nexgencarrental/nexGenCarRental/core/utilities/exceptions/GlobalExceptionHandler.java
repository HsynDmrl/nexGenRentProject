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
    public ErrorResponse handleEntityNotFound(EntityNotFoundException exception) {
        String errorMessage = exception.getMessage();
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Entity Not Found", errorMessage, LocalDateTime.now());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ForbiddenResponse handleAccessDenied(AccessDeniedException exception) {
        String errorMessage = exception.getMessage();
        return new ForbiddenResponse(HttpStatus.FORBIDDEN.value(), "Access Denied", errorMessage, LocalDateTime.now());
    }
}
