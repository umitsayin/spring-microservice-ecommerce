package com.turkcell.invoiceservice.service;

import com.turkcell.invoiceservice.dto.request.InvoiceRequest;
import com.turkcell.invoiceservice.dto.response.InvoiceResponse;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {
    List<InvoiceResponse> getAll();
    InvoiceResponse getById(UUID id);
    InvoiceResponse create(InvoiceRequest request);
    InvoiceResponse updateById(UUID id, InvoiceRequest request);
    void deleteById(UUID id);
}
