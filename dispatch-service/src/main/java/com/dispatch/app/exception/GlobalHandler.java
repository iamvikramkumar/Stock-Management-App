package com.dispatch.app.exception;

import java.time.LocalDate;  // Use LocalDate for just the date
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {

    // Handle InvalidCompanyException
    @ExceptionHandler(InvalidCompanyException.class)
    public ResponseEntity<GlobalErrorResponse> handleInvalidCompanyException(
            InvalidCompanyException exception, HttpServletRequest httpServletRequest) {

        // Create and populate the error response
        GlobalErrorResponse globalErrorResponse = new GlobalErrorResponse(
            exception.getMessage(),  // Error message from the exception
            httpServletRequest.getRequestURI(),  // Request URI where error occurred
            LocalDateTime.now(),  // Used LocalDate to capture the date
            HttpStatus.BAD_REQUEST  // HTTP status for the exception
        );

        return new ResponseEntity<>(globalErrorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle InvalidStockQuantityException
    @ExceptionHandler(InvalidStockQuantityException.class)
    public ResponseEntity<GlobalErrorResponse> handleInvalidStockQuantityException(
            InvalidStockQuantityException exception, HttpServletRequest httpServletRequest) {

        // Create and populate the error response
        GlobalErrorResponse globalErrorResponse = new GlobalErrorResponse(
            exception.getMessage(),  // Error message from the exception
            httpServletRequest.getRequestURI(),  // Request URI where error occurred
            LocalDateTime.now(),  // Used LocalDate to capture the date
            HttpStatus.UNPROCESSABLE_ENTITY  // HTTP status for the exception
        );

        return new ResponseEntity<>(globalErrorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
