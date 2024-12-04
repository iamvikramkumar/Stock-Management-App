package com.payment.app.exception;

public class PaymentNotSavedException extends RuntimeException {

    public PaymentNotSavedException(String message) {
        super(message);
    }
}
