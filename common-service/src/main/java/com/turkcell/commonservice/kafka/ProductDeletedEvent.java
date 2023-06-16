package com.turkcell.commonservice.kafka;

public record ProductDeletedEvent(String productId) implements Event {

}
