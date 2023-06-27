package com.turkcell.commonservice.kafka;

import java.math.BigDecimal;
import java.util.List;

public record SaleCreatedEvent(String saleId, BigDecimal totalPrice, List<String> products) implements Event {

}
