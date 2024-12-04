package com.stock.app.exception;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<GlobalErrorResponse> handleStockNotFoundException(StockNotFoundException ex, HttpServletRequest httpServletRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setRequestPath(httpServletRequest.getRequestURI());
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setRequestStatus(HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InvalidRangeException.class)
    public ResponseEntity<GlobalErrorResponse> handleInvalidRangeException(InvalidRangeException ex, HttpServletRequest httpServletRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setRequestPath(httpServletRequest.getRequestURI());
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setRequestStatus(HttpStatus.FORBIDDEN);
        
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
   
    @ExceptionHandler(StockAlreadyExistsException.class)
    public ResponseEntity<GlobalErrorResponse> handleStockAlreadyExistsException(StockAlreadyExistsException ex, HttpServletRequest httpServletRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setRequestPath(httpServletRequest.getRequestURI());
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setRequestStatus(HttpStatus.FORBIDDEN);
        
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GlobalErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest httpServletRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setRequestPath(httpServletRequest.getRequestURI());
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setRequestStatus(HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

 
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<GlobalErrorResponse> handleNullPointerException(NullPointerException ex, HttpServletRequest httpServletRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setErrorMessage("Unexpected error occurred");
        errorResponse.setRequestPath(httpServletRequest.getRequestURI());
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setRequestStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalErrorResponse> handleGenericException(Exception ex, HttpServletRequest httpServletRequest) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse();
        errorResponse.setErrorMessage("An unexpected error occurred");
        errorResponse.setRequestPath(httpServletRequest.getRequestURI());
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setRequestStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
