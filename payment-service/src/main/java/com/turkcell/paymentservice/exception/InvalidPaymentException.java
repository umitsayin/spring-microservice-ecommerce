package com.turkcell.paymentservice.exception;

public class InvalidPaymentException extends RuntimeException{
    public InvalidPaymentException(String message){
        super(message);
    }
}
