package com.turkcell.saleservice.dto.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record SaleResponse(UUID id, String description, BigDecimal totalPrice, List<String> products) {

}