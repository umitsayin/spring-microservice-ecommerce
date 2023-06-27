package com.turkcell.paymentservice.exception;

import com.turkcell.commonservice.dto.response.ClientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentExceptionHandler {

    @ExceptionHandler(InvalidPaymentException.class)
    public ResponseEntity<ClientResponse> handleInvalidPaymentException(InvalidPaymentException e){
        return ResponseEntity.ok(new ClientResponse(false, e.getMessage()));
    }

    @ExceptionHandler(PaymentBalanceException.class)
    public ResponseEntity<ClientResponse> handlePaymentBalanceException(PaymentBalanceException e){
        return ResponseEntity.ok(new ClientResponse(false, e.getMessage()));
    }
}
