package com.turkcell.saleservice.api.client;

import com.turkcell.commonservice.dto.request.CreatePaymentRequest;
import com.turkcell.commonservice.dto.response.ClientResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentClientFallback implements PaymentClient{
    @Override
    public ClientResponse paymentControl(CreatePaymentRequest request) {
        throw new RuntimeException("PAYMENT-SERVICE DOWN!");
    }
}
