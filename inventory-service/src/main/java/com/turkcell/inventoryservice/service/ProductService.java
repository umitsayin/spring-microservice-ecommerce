package com.turkcell.inventoryservice.service;

import com.turkcell.inventoryservice.dto.request.ProductRequest;
import com.turkcell.inventoryservice.dto.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductResponse> getAll();
    ProductResponse getById(UUID id);
    ProductResponse create(MultipartFile[] files, ProductRequest request);
    ProductResponse update(UUID id, ProductRequest request);
    void deleteById(UUID id);
}
