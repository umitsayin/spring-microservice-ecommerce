package com.turkcell.inventoryservice.dto.response;

import com.turkcell.inventoryservice.model.Product;

import java.math.BigDecimal;
import java.util.List;


public record ProductResponse(String name,
                              String description,
                              int quantity,
                              BigDecimal price,
                              List<CategoryResponse> categories,
                              List<String> documents) {

    public static ProductResponse convert(Product from, List<CategoryResponse> categories, List<String> documents){
        return new ProductResponse(
                from.getName(),
                from.getDescription(),
                from.getQuantity(),
                from.getPrice(),
                categories,
                documents);
    }
}
