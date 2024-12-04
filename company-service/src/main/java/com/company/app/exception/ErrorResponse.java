package com.company.app.exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private String errorMessage;
	
	private String requestPath;
	
	private LocalDateTime timeStamp;
	
	private HttpStatus requestStatus;
	
}