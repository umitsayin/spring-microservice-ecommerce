package com.turkcell.invoiceservice.kafka;

import com.turkcell.commonservice.kafka.SaleCreatedEvent;
import com.turkcell.commonservice.util.constant.CommonConstant;
import com.turkcell.invoiceservice.dto.request.InvoiceRequest;
import com.turkcell.invoiceservice.service.InvoiceService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SaleConsumer {
    private final InvoiceService invoiceService;

    public SaleConsumer(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @KafkaListener(topics = CommonConstant.Kafka.SALE_CREATED_TOPIC,
            groupId = CommonConstant.Kafka.SALE_CREATED_GROUP_ID)
    public void consume(SaleCreatedEvent event){
        InvoiceRequest request = new InvoiceRequest(event.saleId(),event.totalPrice());
        invoiceService.create(request);
    }
}
