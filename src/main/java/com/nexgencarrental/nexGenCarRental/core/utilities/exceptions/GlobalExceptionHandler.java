package com.nexgencarrental.nexGenCarRental.core.utilities.exceptions;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            EntityExistsException.class,
            AccessDeniedException.class,
            IllegalArgumentException.class,
            UsernameNotFoundException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleBadRequest(Exception exception) {
        String errorMessage = exception.getMessage();
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler({
            EntityNotFoundException.class,
            IllegalStateException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFound(Exception exception) {
        String errorMessage = exception.getMessage();
        return new ApiErrorResponse(HttpStatus.NOT_FOUND, errorMessage);
    }

    @ExceptionHandler({
            DataAccessException.class,
            RuntimeException.class,
            Exception.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleInternalServerError(Exception exception) {
        String errorMessage = exception.getMessage();
        return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
    }
}