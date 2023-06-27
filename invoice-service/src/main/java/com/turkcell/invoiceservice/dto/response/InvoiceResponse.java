package com.turkcell.invoiceservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record InvoiceResponse(UUID id,
                              String saleId,
                              BigDecimal totalPrice,
                              LocalDateTime createdAt) {
}
