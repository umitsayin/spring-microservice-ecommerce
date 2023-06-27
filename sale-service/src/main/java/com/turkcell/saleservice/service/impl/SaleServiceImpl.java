package com.turkcell.saleservice.service.impl;

import com.turkcell.commonservice.config.exception.EntityNotFoundException;
import com.turkcell.commonservice.dto.request.CreatePaymentRequest;
import com.turkcell.commonservice.dto.response.ClientResponse;
import com.turkcell.commonservice.kafka.SaleCreatedEvent;
import com.turkcell.commonservice.util.constant.CommonConstant;
import com.turkcell.commonservice.util.kafka.KafkaProducer;
import com.turkcell.saleservice.api.client.PaymentClient;
import com.turkcell.saleservice.dto.request.SaleRequest;
import com.turkcell.saleservice.dto.response.SaleResponse;
import com.turkcell.saleservice.model.Sale;
import com.turkcell.saleservice.model.SaleItem;
import com.turkcell.saleservice.repository.SaleItemRepository;
import com.turkcell.saleservice.repository.SaleRepository;
import com.turkcell.saleservice.service.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final PaymentClient paymentClient;
    private final KafkaProducer producer;

    public SaleServiceImpl(SaleRepository saleRepository,
                           SaleItemRepository saleItemRepository,
                           PaymentClient paymentClient,
                           KafkaProducer producer) {
        this.saleRepository = saleRepository;
        this.saleItemRepository = saleItemRepository;
        this.paymentClient = paymentClient;
        this.producer = producer;
    }

    @Override
    public List<SaleResponse> getAll() {
        List<Sale> sales = saleRepository.findAll();

        return sales.stream()
                .map(sale -> new SaleResponse(sale.getId(),
                        sale.getDescription(),
                        sale.getTotalPrice(),
                        sale.getSaleItems().stream().map(SaleItem::getProductId).toList()))
                .toList();
    }

    @Override
    public SaleResponse getById(UUID id) {
        Sale sale = getSaleById(id);

        return getSaleResponse(sale);
    }

    @Override
    public SaleResponse create(SaleRequest request) {
        Sale sale = new Sale();

        fillSale(request, sale);

        CreatePaymentRequest paymentRequest = new CreatePaymentRequest(request.paymentRequest().cardNumber(),
                request.paymentRequest().cardHolder(),
                request.paymentRequest().cardExpirationYear(),
                request.paymentRequest().cardExpirationMonth(),
                request.paymentRequest().cardCvv(),
                request.totalPrice());

        processPayment(paymentRequest);
        sendSaleCreatedKafkaConsumer(saleRepository.save(sale));

        return getSaleResponse(sale);
    }

    @Override
    public SaleResponse updateById(UUID id, SaleRequest request) {
        Sale sale = getSaleById(id);
        fillSale(request, sale);

        saleRepository.save(sale);

        return getSaleResponse(sale);
    }

    @Override
    public void deleteById(UUID id) {
        Sale sale = getSaleById(id);

        saleRepository.delete(sale);
    }

    private Sale getSaleById(UUID id){
        return saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CommonConstant.Exception.SALES_NOT_FOUND));
    }

    private void fillSale(SaleRequest request, Sale sale){
        sale.setDescription(request.description());
        sale.setTotalPrice(request.totalPrice());
        sale.setSaleItems(request.products().stream()
                .map(item -> saleItemRepository.save(new SaleItem(item))).toList());
    }

    private SaleResponse getSaleResponse(Sale sale){
        List<String> products = sale.getSaleItems().stream().map(SaleItem::getProductId).toList();

        return  new SaleResponse(sale.getId(), sale.getDescription(), sale.getTotalPrice(), products);
    }

    private void processPayment(CreatePaymentRequest request){
        ClientResponse clientResponse = paymentClient.paymentControl(request);

        if(!clientResponse.status()){
            throw new RuntimeException(CommonConstant.Exception.PAYMENT_FAILED);
        }
    }

    private void sendSaleCreatedKafkaConsumer(Sale sale){
        SaleCreatedEvent saleCreatedEvent = new SaleCreatedEvent(sale.getId().toString(),
                sale.getTotalPrice(),
                sale.getSaleItems().stream().map(SaleItem::getProductId).toList());

        producer.sendMessage(CommonConstant.Kafka.SALE_CREATED_TOPIC, saleCreatedEvent);
    }
}
