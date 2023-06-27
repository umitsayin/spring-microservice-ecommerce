package com.turkcell.paymentservice.dto.requset;

import java.math.BigDecimal;

public record PaymentRequest(String cardNumber,
                             String cardHolder,
                             int cardExpirationYear,
                             int cardExpirationMonth,
                             String cardCvv,
                             BigDecimal balance) {
}
