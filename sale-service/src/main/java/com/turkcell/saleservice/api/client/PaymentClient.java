package com.turkcell.saleservice.api.client;

import com.turkcell.commonservice.dto.request.CreatePaymentRequest;
import com.turkcell.commonservice.dto.response.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", fallback = PaymentClientFallback.class)
public interface PaymentClient {

    @PostMapping("/api/payments/check-payment")
    ClientResponse paymentControl(@RequestBody CreatePaymentRequest request);
}
