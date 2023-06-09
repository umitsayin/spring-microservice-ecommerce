package com.turkcell.inventoryservice.service;


import com.turkcell.inventoryservice.dto.request.CategoryRequest;
import com.turkcell.inventoryservice.dto.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryResponse> getAll();
    CategoryResponse getById(UUID id);
    CategoryResponse create(CategoryRequest request);
    CategoryResponse updateById(UUID id, CategoryRequest request);
    void deleteById(UUID id);
}
