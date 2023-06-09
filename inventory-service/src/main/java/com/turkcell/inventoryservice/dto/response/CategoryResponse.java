package com.turkcell.inventoryservice.dto.response;

import com.turkcell.inventoryservice.model.Category;

import java.util.UUID;

public record CategoryResponse(UUID id, String name) {

    public static CategoryResponse convert(Category from){
        return new CategoryResponse(from.getId(), from.getName());
    }
}
