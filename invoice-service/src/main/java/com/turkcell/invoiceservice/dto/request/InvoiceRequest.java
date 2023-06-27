package com.turkcell.invoiceservice.dto.request;

import java.math.BigDecimal;

public record InvoiceRequest(String saleId, BigDecimal totalPrice) {
}
