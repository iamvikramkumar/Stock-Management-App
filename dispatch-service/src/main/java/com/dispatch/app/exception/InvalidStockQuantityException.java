package com.dispatch.app.exception;

public class InvalidStockQuantityException extends RuntimeException{
public InvalidStockQuantityException(String msg) {
	super(msg);
}
}
