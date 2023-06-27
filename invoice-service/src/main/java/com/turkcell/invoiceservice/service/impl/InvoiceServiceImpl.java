package com.turkcell.invoiceservice.service.impl;

import com.turkcell.commonservice.config.exception.EntityNotFoundException;
import com.turkcell.commonservice.util.constant.CommonConstant;
import com.turkcell.invoiceservice.dto.request.InvoiceRequest;
import com.turkcell.invoiceservice.dto.response.InvoiceResponse;
import com.turkcell.invoiceservice.model.Invoice;
import com.turkcell.invoiceservice.repository.InvoiceRepository;
import com.turkcell.invoiceservice.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository repository;

    public InvoiceServiceImpl(InvoiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InvoiceResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        List<InvoiceResponse> response = invoices.stream().map(this::createResponse).toList();

        return response;
    }

    @Override
    public InvoiceResponse getById(UUID id) {
        Invoice invoice = getInvoiceById(id);
        InvoiceResponse response = createResponse(invoice);

        return response;
    }

    @Override
    public InvoiceResponse create(InvoiceRequest request) {
        Invoice invoice = new Invoice();

        invoice.setSaleId(request.saleId());
        invoice.setTotalPrice(request.totalPrice());

        Invoice savedInvoice = repository.save(invoice);
        InvoiceResponse response = new InvoiceResponse(savedInvoice.getId(),
                savedInvoice.getSaleId(),
                savedInvoice.getTotalPrice(),
                savedInvoice.getCreatedAt());

        return response;
    }

    @Override
    public InvoiceResponse updateById(UUID id, InvoiceRequest request) {
        Invoice invoice = getInvoiceById(id);

        invoice.setSaleId(request.saleId());
        invoice.setTotalPrice(request.totalPrice());

        Invoice updatedInvoice = repository.save(invoice);
        InvoiceResponse response = new InvoiceResponse(updatedInvoice.getId(),
                updatedInvoice.getSaleId(),
                updatedInvoice.getTotalPrice(),
                updatedInvoice.getCreatedAt());

        return response;
    }

    @Override
    public void deleteById(UUID id) {
        repository.delete(getInvoiceById(id));
    }

    private Invoice getInvoiceById(UUID id){
        return repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(CommonConstant.Exception.INVOICE_NOT_FOUND));
    }

    private InvoiceResponse createResponse(Invoice invoice){
        return new InvoiceResponse(invoice.getId(),invoice.getSaleId(),invoice.getTotalPrice(),invoice.getCreatedAt());
    }
}
