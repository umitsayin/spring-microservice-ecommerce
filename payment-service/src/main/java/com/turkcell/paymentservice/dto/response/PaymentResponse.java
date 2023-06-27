package com.turkcell.paymentservice.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentResponse(UUID id,
                              String cardNumber,
                              String cardHolder,
                              int cardExpirationYear,
                              int cardExpirationMonth,
                              String cardCvv,
                              BigDecimal balance) {
}
