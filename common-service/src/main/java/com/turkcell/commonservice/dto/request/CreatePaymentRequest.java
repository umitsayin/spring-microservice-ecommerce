package com.turkcell.commonservice.dto.request;

import java.math.BigDecimal;

public record CreatePaymentRequest(String cardNumber,
                                   String cardHolder,
                                   int cardExpirationYear,
                                   int cardExpirationMonth,
                                   String cardCvv,
                                   BigDecimal price
                                   ) {

}
