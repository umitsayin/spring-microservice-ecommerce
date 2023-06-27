package com.turkcell.paymentservice.exception;

public class PaymentBalanceException extends RuntimeException{
    public PaymentBalanceException(String message){
        super(message);
    }
}
