package com.turkcell.saleservice.dto.request;

import com.turkcell.commonservice.dto.request.CreatePaymentRequest;

import java.math.BigDecimal;
import java.util.List;

public record SaleRequest(String description,
                          BigDecimal totalPrice,
                          List<String> products,
                          CreatePaymentRequest paymentRequest) {

}
