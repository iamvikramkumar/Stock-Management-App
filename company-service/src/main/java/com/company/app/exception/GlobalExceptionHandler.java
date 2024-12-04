package com.company.app.exception;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NoSuchElementException ex, HttpServletRequest httpServletRequest) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestPath(httpServletRequest.getRequestURI());
        error.setTimeStamp(LocalDateTime.now());
        error.setRequestStatus(HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
	
	

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ErrorResponse> handleBadRequestException(IllegalArgumentException ex) {
//        ErrorResponse error = new ErrorResponse("Bad request: " + ex.getMessage());
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
//        ErrorResponse error = new ErrorResponse("An error occurred: " + ex.getMessage());
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex) {
//        ErrorResponse error = new ErrorResponse("An error occurred: " + ex.getMessage());
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
	
}
