package com.turkcell.documentservice.kafka;

import com.turkcell.commonservice.kafka.ProductDeletedEvent;
import com.turkcell.commonservice.util.constant.CommonConstant;
import com.turkcell.documentservice.service.DocumentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumer {
    private final DocumentService documentService;

    public InventoryConsumer(DocumentService documentService) {
        this.documentService = documentService;
    }

    @KafkaListener(topics = CommonConstant.Kafka.PRODUCT_DELETED_TOPIC,
            groupId = CommonConstant.Kafka.PRODUCT_DELETED_GROUP_ID)
    public void consume(ProductDeletedEvent event){
        documentService.deleteAllByProductId(event.productId());
    }
}
