package com.turkcell.inventoryservice.kafka;

import com.turkcell.commonservice.config.exception.EntityNotFoundException;
import com.turkcell.commonservice.kafka.SaleCreatedEvent;
import com.turkcell.commonservice.util.constant.CommonConstant;
import com.turkcell.inventoryservice.model.Product;
import com.turkcell.inventoryservice.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class SaleConsumer {
    private final ProductRepository repository;

    public SaleConsumer(ProductRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = CommonConstant.Kafka.SALE_CREATED_TOPIC,
            groupId = CommonConstant.Kafka.SALE_CREATED_GROUP_ID)
    public void consume(SaleCreatedEvent event){
        event.products().forEach(item-> {
            Product product = repository.findById(UUID.fromString(item))
                    .orElseThrow(()-> new EntityNotFoundException(CommonConstant.Exception.PRODUCT_NOT_FOUND));

            product.setQuantity(product.getQuantity() -1);

            repository.save(product);
        });
    }
}
