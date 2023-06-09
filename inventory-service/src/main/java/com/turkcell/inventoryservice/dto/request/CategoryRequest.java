package com.turkcell.inventoryservice.dto.request;

public class CategoryRequest {
    private String name;

    public CategoryRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
