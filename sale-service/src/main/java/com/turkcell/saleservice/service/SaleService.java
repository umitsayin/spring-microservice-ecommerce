package com.turkcell.saleservice.service;

import com.turkcell.saleservice.dto.request.SaleRequest;
import com.turkcell.saleservice.dto.response.SaleResponse;

import java.util.List;
import java.util.UUID;

public interface SaleService {
    List<SaleResponse> getAll();
    SaleResponse getById(UUID id);
    SaleResponse create(SaleRequest saleRequest);
    SaleResponse updateById(UUID id, SaleRequest request);
    void deleteById(UUID id);
}
