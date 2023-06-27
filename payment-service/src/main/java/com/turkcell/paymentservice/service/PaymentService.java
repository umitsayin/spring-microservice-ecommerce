package com.turkcell.paymentservice.service;

import com.turkcell.commonservice.dto.request.CreatePaymentRequest;
import com.turkcell.commonservice.dto.response.ClientResponse;
import com.turkcell.paymentservice.dto.requset.PaymentRequest;
import com.turkcell.paymentservice.dto.response.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<PaymentResponse> getAll();
    PaymentResponse getById(UUID id);
    PaymentResponse create(PaymentRequest request);
    PaymentResponse updateById(UUID id, PaymentRequest request);
    void deleteById(UUID id);
    ClientResponse paymentControl(CreatePaymentRequest request);
}
